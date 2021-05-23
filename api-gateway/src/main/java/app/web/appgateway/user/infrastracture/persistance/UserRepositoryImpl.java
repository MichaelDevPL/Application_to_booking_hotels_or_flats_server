package app.web.appgateway.user.infrastracture.persistance;

import app.web.appgateway.user.domain.User;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class UserRepositoryImpl extends SimpleJpaRepository<User, Long> implements UserRepository {

    private EntityManager em;

    public UserRepositoryImpl(EntityManager em) {
        super(User.class, em);
        this.em = em;
    }

    @Override
    public Optional<User> getUserById(long userId) {
        String sqlQuery = "Select u from User u where u.id = :id";
        Query query = em.createQuery(sqlQuery);
        query.setParameter("id", userId);

        List<User> user = query.getResultList();

        // todo MK there needs to be created mechanism of throwing 404 via REST
        if (user.isEmpty()) {
            throw new IllegalArgumentException("No user with id:" + userId);
        }

        return Optional.of(user.get(0));

    }

    @Override
    public User getUserByAccountId(long accountId) {
        String sqlQuery = " select u from User u" +
                " join fetch u.account a" +
                " where a.id=:accountId";

        Query query = em.createQuery(sqlQuery);

        query.setParameter("accountId", accountId);

        return (User) query.getResultList().get(0);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        String sqlQuery = "Select u from User u where u.email = :email";
        Query query = em.createQuery(sqlQuery);
        query.setParameter("email", email);

        List<User> user = query.getResultList();

        // todo MK there needs to be created mechanism of throwing 404 via REST
        if (user.isEmpty()) {
            throw new IllegalArgumentException("No user with email: " + email);
        }

        return Optional.of(user.get(0));

    }

    @Override
    public boolean userExistsByEmail(String email) {
        String sqlQuery = "Select u from User u where u.email = :email";
        Query query = em.createQuery(sqlQuery);
        query.setParameter("email", email);

        List<User> results = query.getResultList();

        return results.isEmpty();
    }

    @Override
    public void createUser(User user) {
        save(user);
    }

    @Override
    public void updateUser(User user) {
        user.setAccount(getUserById(user.getId())
                .map(User::getAccount).orElseThrow(() -> new IllegalArgumentException("No user with ID: " + user.getId())));
        save(user);
    }
}
