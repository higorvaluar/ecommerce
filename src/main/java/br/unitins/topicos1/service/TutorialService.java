package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.TutorialRequestDTO;
import br.unitins.topicos1.dto.TutorialResponseDTO;
import br.unitins.topicos1.model.Tutorial;
import jakarta.validation.Valid;

import java.util.List;

public interface TutorialService {
    public TutorialResponseDTO create (@Valid TutorialRequestDTO tutorialDTO);
    public void update(Long id, TutorialRequestDTO tutorialDTO);
    public void delete(Long id);
    public TutorialResponseDTO findById(Long id);
    public List<TutorialResponseDTO> findAll();
    public List<TutorialResponseDTO> findByProduto(Long nomeProduto);
}
