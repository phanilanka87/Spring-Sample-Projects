package com.jpa.auto.repository;

import com.jpa.auto.domain.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Long> {
    
    public List<Person> findPersonByFName(String fName);
    
    @Query("select p from Person p where p.fName like %:name% OR p.lName like %:name%")
    public List<Person> findPersonByFNameOrLNameContains(@Param("name") String name);
}
