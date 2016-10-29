package br.com.imarket.istoque.user;

import java.util.Optional;

import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepository {

	Optional<User> findByEmail(String username);

}
