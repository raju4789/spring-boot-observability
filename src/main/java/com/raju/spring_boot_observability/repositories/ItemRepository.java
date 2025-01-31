package com.raju.spring_boot_observability.repositories;

import com.raju.spring_boot_observability.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
