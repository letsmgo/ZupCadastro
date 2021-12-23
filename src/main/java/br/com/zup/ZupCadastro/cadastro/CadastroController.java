package br.com.zup.ZupCadastro.cadastro;

import br.com.zup.ZupCadastro.cadastro.dto.CadastroDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastros")
public class CadastroController {
    @Autowired
    private CadastroService cadastroService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void realizarCadastro(@RequestBody CadastroDto cadastroDto){
        Cadastro cadastro = modelMapper.map(cadastroDto, Cadastro.class);

        cadastroService.salvarCadastro(cadastro);
    }

}
