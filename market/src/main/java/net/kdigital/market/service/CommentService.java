package net.kdigital.market.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.kdigital.market.dto.CommentDTO;
import net.kdigital.market.entity.CommentEntity;
import net.kdigital.market.repository.BoardRepository;
import net.kdigital.market.repository.CommentRepository;
import net.kdigital.market.repository.MemRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository repository;
    private final BoardRepository boardRepository;
    private final MemRepository memRepository;

    /**
     * 전달받은 boardNum에 해당하는 게시글의 comment를 List로 반환하는 함수
     * 
     * @param boardNum
     * @return
     */
    public List<CommentDTO> selectAll(Long boardNum) {
        List<CommentEntity> entityList = repository.findAll();
        List<CommentDTO> dtoList = new ArrayList<>();

        entityList.forEach((entity) -> {
            dtoList.add(CommentDTO.toDTO(entity, boardNum, entity.getMemEntity().getMemId()));
        });

        return dtoList;
    }

    /**
     * 전달받은 코멘트 dto를 entity로 변환하여 DB에 등록하는 함수
     * 
     * @param commentDTO
     * @return
     */
    public CommentDTO insert(CommentDTO commentDTO) {

        CommentEntity entity = CommentEntity.toEntity(commentDTO,
                boardRepository.findById(commentDTO.getBoardNum()).get(),
                memRepository.findById(commentDTO.getMemId()).get());

        CommentEntity result = repository.save(entity);

        return CommentDTO.toDTO(result, result.getBoardEntity().getBoardNum(), result.getMemEntity().getMemId());
    }

}
