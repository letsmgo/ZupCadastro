package br.com.zup.ZupCadastro.cadastro;

import br.com.zup.ZupCadastro.cadastro.dto.CadastroDto;
import br.com.zup.ZupCadastro.cadastro.dto.CadastroResumoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping
    public List<CadastroResumoDto> exibirResumoDeCadastros(@RequestParam(required = false) Boolean moraSozinho,
                                                           @RequestParam(required = false) Integer idade,
                                                           @RequestParam(required = false) Boolean temPet){
        List<CadastroResumoDto> cadastroDto = new ArrayList<>();
        for (Cadastro cadastro : cadastroService.exibirtodosOsCadastros(moraSozinho, idade, temPet)){
            CadastroResumoDto cadastroResumoDtos = modelMapper.map(cadastro, CadastroResumoDto.class);
            cadastroDto.add(cadastroResumoDtos);
        }
        return cadastroDto;
    }

    @DeleteMapping("/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCadastro(@PathVariable String cpf){
        cadastroService.deletarCadastro(cpf);
    }

}
