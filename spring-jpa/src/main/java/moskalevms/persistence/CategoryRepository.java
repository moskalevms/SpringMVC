package moskalevms.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import moskalevms.persistence.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select new Category(c.id, c.name, c.description) from Category c")
    List<Category> findAllWithoutProducts();

    @Query("select distinct c " +
            "from Category c " +
            "left join fetch c.products p " +
            "where c.id = :id")
    Optional<Category> findByIdWithProducts(@Param("id") Long id);
}
