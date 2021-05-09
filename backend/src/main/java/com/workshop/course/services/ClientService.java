package com.workshop.course.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.workshop.course.dto.ClientDTO;
import com.workshop.course.dto.ClientNewDTO;
import com.workshop.course.entities.Address;
import com.workshop.course.entities.City;
import com.workshop.course.entities.Client;
import com.workshop.course.entities.enums.ClientType;
import com.workshop.course.entities.enums.Profile;
import com.workshop.course.repository.AddressRepository;
import com.workshop.course.repository.ClientRepository;
import com.workshop.course.security.UserSS;
import com.workshop.course.services.exeptions.AuthorizationException;
import com.workshop.course.services.exeptions.DatabaseException;
import com.workshop.course.services.exeptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
		
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;

	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		List<Client> list = repository.findAll();
		return list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> list = repository.findAll(pageRequest);
		return list.map(x -> new ClientDTO(x));
	}

	@Transactional(readOnly = true)
	public Client findById(Long id) {

		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found Id: " + id));
		return entity;
	}
	
	@Transactional(readOnly = true)
	public Client findByEmail(String email) {
		
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Profile.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
	
		Client obj = repository.findByEmail(email);
		if (obj == null) {
			throw new ResourceNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Client.class.getName());
		}
		return obj;
	}

	@Transactional
	public Client insert(Client entity) {
		try {
			entity = repository.save(entity);
			addressRepository.saveAll(entity.getAddress());
			return entity;
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Erro no cadastro");
		}
	}

	@Transactional
	public Client update(Long id, Client entity) {
		try {
			Client newEntity = repository.getOne(id);
			updateData(newEntity, entity);
			return repository.save(newEntity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade");
		}
	}

	public Client copyDtoToEntity(ClientDTO dto) {
		Client cli = new Client(dto.getId(), dto.getName(), dto.getEmail(), null, null, null);
		return cli;
	}

	public Client copyDtoToEntity(ClientNewDTO dto) {

		Client cli = new Client(null, dto.getName(), dto.getEmail(), dto.getCpfCnpj(), ClientType.toEnum(dto.getType()),
				pe.encode(dto.getPassword()));
		City cit = new City(dto.getCityId(), null, null);
		Address add = new Address(null, dto.getStreet(), dto.getNum(), dto.getComplement(), dto.getDistrict(),
				dto.getCep(), cli, cit);

		cli.getAddress().add(add);

		cli.getPhone().add(dto.getPhone1());
		if (dto.getPhone2() != null) {
			cli.getPhone().add(dto.getPhone2());
		}
		if (dto.getPhone3() != null) {
			cli.getPhone().add(dto.getPhone3());
		}

		return cli;
	}

	private void updateData(Client entity, Client dto) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		String fileName = prefix + user.getId() + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
}