package auth.domain.service;

import auth.domain.Credential;
import auth.domain.repository.CredentialRepository;
import auth.domain.resource.vo.CredentialVO;
import domain.NewUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Credentials Service
 *
 * @author Claudio E. de Oliveira on on 21/04/16.
 */
@Service
@Log4j2
public class CredentialService {

    private final CredentialRepository credentialRepository;

    private final RegisterUserService registerUserService;

    @Autowired
    public CredentialService(CredentialRepository credentialRepository,RegisterUserService registerUserService) {
        this.credentialRepository = credentialRepository;
        this.registerUserService = registerUserService;
    }

    /**
     * Creates a new Owner
     *
     * @param credential
     * @return
     */
    public Credential createOwner(CredentialVO credential) {
        log.info("Creating new [OWNER]");
        Credential newCredential = Credential.fromVO(credential.getEmail(), credential.getNickname(), credential.getPassword());
        this.registerUserService.registerNewUser(newCredential);
        return this.credentialRepository.addOwner(newCredential);
    }

    /**
     * Creates a new Maintainer
     *
     * @param credential
     * @return
     */
    public Credential createMaintainer(CredentialVO credential) {
        log.info("Creating new [MAINTAINER]");
        Credential newCredential = Credential.fromVO(credential.getEmail(), credential.getNickname(), credential.getPassword());
        this.registerUserService.registerNewUser(newCredential);
        return this.credentialRepository.addMaintainer(newCredential);
    }

    /**
     * Creates a new User
     *
     * @param credential
     * @return
     */
    public Credential createUser(CredentialVO credential) {
        log.info("Creating new [USER]");
        Credential newCredential = Credential.fromVO(credential.getEmail(), credential.getNickname(), credential.getPassword());
        this.registerUserService.registerNewUser(newCredential);
        return this.credentialRepository.addUser(newCredential);
    }

}
