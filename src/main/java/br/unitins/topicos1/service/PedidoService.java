package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.PedidoRequestDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface PedidoService {
    public PedidoResponseDTO create(@Valid PedidoRequestDTO pedidoRequestDTO);

    public void update(Long id, PedidoRequestDTO PedidoRequestDTO);

    public void delete(Long id);

    public PedidoResponseDTO findById(Long id);

    public List<PedidoResponseDTO> findAll();

    public List<PedidoResponseDTO> findByUsuario(Long usuarioId);
}
