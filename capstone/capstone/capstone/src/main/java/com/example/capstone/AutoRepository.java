package com.example.capstone;


import org.springframework.data.repository.CrudRepository;
import com.example.capstone.AutoPolicyQuote;
public interface AutoRepository extends CrudRepository<AutoPolicyQuote, Integer> {

    //There is nothing in here. Hibernate handles all the CRUD
    //IF you needed a very special or different query, you would write it here.
    //See docs before attempting anything like that.
}
