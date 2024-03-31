package net.kdigital.market.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.market.dto.LoginMemDetails;
import net.kdigital.market.dto.MemDTO;
import net.kdigital.market.entity.MemEntity;
import net.kdigital.market.repository.MemRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginUserDetailsService implements UserDetailsService {
    private final MemRepository memRepository;

    @Override
    public UserDetails loadUserByUsername(String memId) throws UsernameNotFoundException {
        log.info("memId : {}", memId);

        MemEntity memEntity = memRepository.findById(memId).orElseThrow(() -> {
            throw new UsernameNotFoundException("error 발생, 존재하는 아이디 없음");
        });

        MemDTO memDTO = MemDTO.toDTO(memEntity);
        // 반환을 MemDtails로 반환해야 하므로 MemDTO를 UserDetails 형태로 바꿔야 함
        return new LoginMemDetails(memDTO);
    }

}
