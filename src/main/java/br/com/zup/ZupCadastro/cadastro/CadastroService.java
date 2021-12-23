package br.com.zup.ZupCadastro.cadastro;

import br.com.zup.ZupCadastro.cadastro.exceptions.CadastroDuplicadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CadastroService {
    @Autowired
    private CadastroRepository cadastroRepository;

    public Cadastro salvarCadastro(Cadastro cadastro) {
        if (cadastroRepository.existsById(cadastro.getCpf())){
            throw new CadastroDuplicadoException();
        }
        cadastro.setDataDoCadastro(LocalDate.now());
        return cadastroRepository.save(cadastro);
    }

}
