package net.kdigital.market.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.kdigital.market.dto.BoardDTO;
import net.kdigital.market.dto.MemDTO;
import net.kdigital.market.entity.BoardEntity;
import net.kdigital.market.entity.CommentEntity;
import net.kdigital.market.entity.MemEntity;
import net.kdigital.market.repository.BoardRepository;
import net.kdigital.market.repository.CommentRepository;
import net.kdigital.market.repository.MemRepository;

@Service
@RequiredArgsConstructor
public class MemService {
    private final MemRepository repository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 전달받은 dto를 entity로 변환하여 DB에 저장
     * 
     * @param memDTO
     */
    public boolean insert(MemDTO memDTO) {
        boolean isExistUser = repository.existsById(memDTO.getMemId());

        // 이미 존재하는 아이디는 가입 못하도록
        if (isExistUser)
            return false;

        // enabled(계정상태) true(사용가능)로 변경
        memDTO.setEnabled(true);

        // 비밀번호 암호화
        memDTO.setMemPw(bCryptPasswordEncoder.encode(memDTO.getMemPw()));

        // dto -> entity
        MemEntity entity = MemEntity.toEntity(memDTO);

        // DB에 저장
        repository.save(entity);

        return true;

    }

    /**
     * 전달 받은 멤버 아이디에 해당하는 회원이 판매중인 상품을 리스트로 반환하는 함수
     * 
     * @param memId
     * @return
     */
    public List<BoardDTO> myBoardList(String memId) {
        Optional<MemEntity> entity = repository.findById(memId);
        List<BoardDTO> boardDTOList = new ArrayList<>();

        if (entity.isPresent()) {
            MemEntity memEntity = entity.get();
            List<BoardEntity> boardEntityList = boardRepository.findAllByMemEntityOrderByBoardNumDesc(memEntity);

            boardEntityList.forEach((e) -> {
                boardDTOList.add(BoardDTO.toDTO(e, memId));
            });
        }
        return boardDTOList;
    }

    public List<BoardDTO> myWishList(String memId) {
        return null;
    }

    /**
     * 전달받은 회원 아이디가 코멘트를 작성한 게시글 리스트 반환
     * 
     * @param memId
     * @return
     */
    public List<BoardDTO> myCommentList(String memId) {

        // memId에 해당하는 모든 comment entity를 list로 가져옴
        MemEntity memEntity = repository.findById(memId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByMemEntityOrderByCommentNumDesc(memEntity);

        // memId가 작성한 코멘트가 담긴 게시글 DTO를 담을 변수
        List<BoardDTO> dtoList = new ArrayList<>();

        commentEntityList.forEach((entity) -> {
            dtoList.add(BoardDTO.toDTO(entity.getBoardEntity(), memId));
        });

        // 중복제거를 위해 Set 리스트에 넣기
        Set<BoardDTO> setList = new LinkedHashSet<>(dtoList);

        return new ArrayList<>(setList);

    }

    /**
     * 전달받은 회원이 구매한 상품 게시글 DTO 리스트 반환
     * 
     * @param memId
     * @return
     */
    public List<BoardDTO> myPurchasedList(String memId) {

        List<BoardEntity> boardEntityList = boardRepository.findById(memId);

        List<BoardDTO> boardDTOList = new ArrayList<>();

        boardEntityList.forEach((entity) -> {
            boardDTOList.add(BoardDTO.toDTO(entity, memId));
        });

        return boardDTOList;
    }

}
