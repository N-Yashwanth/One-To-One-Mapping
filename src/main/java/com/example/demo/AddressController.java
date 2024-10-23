package com.example.demo;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressRepository addressRepository;
	@PostMapping
	public Address createAddress(@RequestBody Address address) {
		return addressRepository.save(address);
	}
	@GetMapping
	public List<Address> getAllAddress(){
		return addressRepository.findAll();
	}
	@GetMapping("/{id}")
	public Address getAddressByID(@PathVariable int id) {
		return addressRepository.findById(id).orElse(null);
	}
	@PutMapping("/{id}")
	public Address updateAddress(@PathVariable int id, @RequestBody Address addressDetails) {
		Address address=addressRepository.findById(id).orElse(null);
		if(addressDetails.getCity()!=null) {
			address.setCity(addressDetails.getCity());
		}
		return addressRepository.save(address);
	}
	@DeleteMapping("/{id}")
	public String deleteAddress(@PathVariable int id) {
		if(addressRepository.existsById(id)) {
			addressRepository.deleteById(id);
			return "Address Deleted Successfully";
		}
		else {
			return "Address not found";
		}
	}
	@GetMapping("/page/{pageNo}/{pageSize}")
	public List<Address> getPaginated(@PathVariable int pageNo, @PathVariable int pageSize){
		Pageable pageable=PageRequest.of(pageNo, pageSize);
		Page<Address> result=addressRepository.findAll(pageable);
		return result.hasContent()?result.getContent():new ArrayList<>();
	}
	@GetMapping("/sort")
	public List<Address> getSorted(@RequestBody String field, @RequestBody String direction){
		Direction sortDirection=direction.equalsIgnoreCase("desc")?Direction.DESC:Direction.ASC;
		return addressRepository.findAll(Sort.by(sortDirection,field));
	}
	@GetMapping("/page/{pageNo}/{pageSize}/sort")
	public List<Address> getPaginatedandSorted(@PathVariable int pageNo, @PathVariable int pageSize, @RequestBody String field, @RequestBody String direction){
		Sort sort=direction.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(field).ascending():Sort.by(field).descending();
		Pageable pageable=PageRequest.of(pageNo, pageSize);
		Page<Address> result=addressRepository.findAll(pageable);
		return result.hasContent()?result.getContent():new ArrayList<>();
	}
}
