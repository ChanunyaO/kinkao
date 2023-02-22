package ku.kinkao.service;

import ku.kinkao.model.Member;
import ku.kinkao.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SignupService {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean isUsernameAvailable(String username) {
        return repository.findByUsername(username) == null;
    }

    public int createMember(Member member) {
        Member newMember = new Member();
        newMember.setFirstName(member.getFirstName());
        newMember.setLastName(member.getLastName());
        newMember.setUsername(member.getUsername());

        String hashedPassword = passwordEncoder.encode(member.getPassword());

        newMember.setPassword(hashedPassword);

        repository.save(newMember);
        return 1;
    }

    public Member getMember(String username) {
        return repository.findByUsername(username);
    }
}
