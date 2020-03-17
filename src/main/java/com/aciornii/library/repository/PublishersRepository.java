package com.aciornii.library.repository;

import com.aciornii.library.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishersRepository extends JpaRepository<Publisher, Long> {

    Publisher findById(long id);
    Publisher findByName(String name);

}
