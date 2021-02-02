package app.web.appgateway.user.infrastracture.persistance;

import app.web.appgateway.user.domain.Account;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class AccountRepositoryImpl extends SimpleJpaRepository<Account, Long> implements AccountRepository {

    private EntityManager em;

    public AccountRepositoryImpl(EntityManager em) {
        super(Account.class, em);
        this.em = em;
    }

    @Override
    public Optional<Account> getAccountById(Long accountId) {

        String slqQuery = "Select a from Account a where a.id = :id";
        Query query = em.createQuery(slqQuery);
        query.setParameter("id", accountId);

        List<Account> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            throw new IllegalArgumentException("No account with such id: " + accountId);
        }

        return Optional.of(resultList.get(0));
    }

    @Override
    public Optional<Account> getByLogin(String login) {

        String slqQuery = "Select a from Account a where a.login = :login";
        Query query =em.createQuery(slqQuery);
        query.setParameter("login", login);

        List<Account> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            throw new IllegalArgumentException("No account with such nick");
        }

        return Optional.of(resultList.get(0));
    }

    @Override
    public boolean verificationLoginAndPassword(String login, String password) {

        String sqlQuery = "Select a from Account a where a.login = :login and a.password = :password";
        Query query = em.createQuery(sqlQuery);
        query.setParameter("login", login)
                .setParameter("password", password);

        List<Account> results = query.getResultList();

        return results != null && !results.isEmpty();
    }

    @Override
    public void createAccount(Account account) {
        save(account);
    }

    @Override
    public Account updateAccount(Account account) {
        return save(account);
    }

    @Override
    public void deleteAccount(Account account) {
        delete(account);
    }

    @Override
    public boolean existsByLogin(String login) {
        String sqlQuery = "Select a from Account a where a.login = :login";
        Query query = em.createQuery(sqlQuery);
        query.setParameter("login", login);

        List<Account> results = query.getResultList();

        return results.isEmpty();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Account account = this.getByLogin(username).orElseThrow(()-> new UsernameNotFoundException("User ["+ username + "] not found during authorization"));

        return org.springframework.security.core.userdetails.User//
                .withUsername(account.getLogin())//
                .password(account.getPassword())//
                .authorities(account.getRole())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }
}