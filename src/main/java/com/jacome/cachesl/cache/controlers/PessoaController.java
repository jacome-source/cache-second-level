package com.jacome.cachesl.cache.controlers;

import com.jacome.cachesl.cache.dtos.PessoaDTO;
import com.jacome.cachesl.cache.model.Pessoa;
import com.jacome.cachesl.cache.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import net.sf.ehcache.CacheManager;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
public class PessoaController {

    private final PessoaRepository pessoaRepository;

    @RequestMapping("/pessoa")
    public List<Pessoa> findPessoas() {
        var pessoas = pessoaRepository.findAll();
        return pessoas;
    }

    @RequestMapping("/pessoa/{id}")
    public Pessoa findPessoa(@PathVariable("id")int id) {
        return pessoaRepository.findById(id).get();
    }

    @RequestMapping("/pessoa/nome/{nome}")
    public List<Pessoa> findByNomeExato(@PathVariable("nome")String nome) {
        return pessoaRepository.findByNome(nome);
    }

    @RequestMapping("/pessoa/inicial/{nome}")
    public List<Pessoa> findByIniciais(@PathVariable("nome")String nome) {
        return pessoaRepository.findByNomeIncompleto(nome+"%");
    }


    @RequestMapping(value = "/pessoa", method = RequestMethod.POST)
    @Transactional
    public Pessoa saveReservation(@RequestBody PessoaDTO request) {
        System.out.println("Saving Pessoa" );
        var pessoa = Pessoa.builder()
                                .nome(request.getNome())
                                .cpf(request.getCpf())
                                .build();

        return pessoaRepository.save(pessoa);

    }

}
