package com.gestao.ui;

import com.gestao.model.Usuario;
import com.gestao.service.ProjetoService;

import java.awt.*;

public class CadastroProjetoDialog extends CadastroProjetoForm {
    public CadastroProjetoDialog(Window owner, Usuario usuario, ProjetoService projetoService, Runnable onSaved) {
        super(owner, usuario, projetoService, onSaved);
    }
}
