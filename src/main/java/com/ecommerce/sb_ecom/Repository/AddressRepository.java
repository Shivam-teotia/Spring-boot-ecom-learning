package com.ecommerce.sb_ecom.Repository;

import com.ecommerce.sb_ecom.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
