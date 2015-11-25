package hotpans.repository;

import hotpans.domain.Bread;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BreadRepository extends JpaRepository<Bread, Integer> {

}
