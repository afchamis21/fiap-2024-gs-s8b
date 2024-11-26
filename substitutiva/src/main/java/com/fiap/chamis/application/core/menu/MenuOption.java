package com.fiap.chamis.application.core.menu;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.fiap.chamis.application.core.ServiceContext;
import com.fiap.chamis.application.domain.DadoEnergia;
import com.fiap.chamis.application.domain.FonteRenovavel;
import com.fiap.chamis.application.domain.Investimento;
import com.fiap.chamis.application.domain.ProjetoEnergia;
import com.fiap.chamis.application.domain.Usuario;
import com.fiap.chamis.application.repo.core.DbManager;
import com.fiap.chamis.application.service.DadoEnergiaService;
import com.fiap.chamis.application.service.FonteRenovavelService;
import com.fiap.chamis.application.service.InvestimentoService;
import com.fiap.chamis.application.service.ProjetoEnergiaService;
import com.fiap.chamis.application.service.UsuarioService;
import com.fiap.chamis.util.InputUtils;

public enum MenuOption {
    // BASE MENU
    HOME_TO_ADMIN(MenuLevel.BASE, -99, "Ir para o menu ADMIN", false,() -> {
        ServiceContext.getInstance().setMenuLevel(MenuLevel.ADMIN);
    }),
    EXIT(MenuLevel.BASE, -1, "Sair do programa", () -> {
        System.exit(0);
    }),

    // HOME MENU
    HOME_TO_USUARIO(MenuLevel.HOME, 2, "Ir para o menu USUARIO", () -> {
        ServiceContext.getInstance().setMenuLevel(MenuLevel.USER);
    }),
    HOME_TO_FONTE_RENOVAVEL(MenuLevel.HOME, 3, "Ir para o menu FONTE RENOVAVEL", () -> {
        ServiceContext.getInstance().setMenuLevel(MenuLevel.RENEWABLE_SOURCE);
    }),
    HOME_TO_PROJETO(MenuLevel.HOME, 4, "Ir para o menu PROJETO", () -> {
        ServiceContext.getInstance().setMenuLevel(MenuLevel.ENERGY_PROJECT);
    }),
    HOME_TO_INVESTIMENTO(MenuLevel.HOME, 5, "Ir para o menu INVESTIMENTO", () -> {
        ServiceContext.getInstance().setMenuLevel(MenuLevel.INVESTIMENT);
    }),
    HOME_TO_DADO_ENERGIA(MenuLevel.HOME, 6, "Ir para o menu DADO DE ENERGIA", () -> {
        ServiceContext.getInstance().setMenuLevel(MenuLevel.ENERGY_DATA);
    }),
    REGISTER(MenuLevel.HOME, 8, "Cadastrar", false, () -> {
        UsuarioService usuarioService = new UsuarioService();
        try {
            usuarioService.criarUsuario(
                    InputUtils.getStringInput("Digite o nome do usuário"),
                    InputUtils.getStringInput("Digite o email do usuário"),
                    InputUtils.getStringInput("Digite a senha do usuário"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }),
    LOGIN(MenuLevel.HOME, 9, "Entrar", false, () -> {
        UsuarioService usuarioService = new UsuarioService();
        try {
            Usuario usuario = usuarioService.autenticarUsuario(
                    InputUtils.getStringInput("Digite o email do usuário"),
                    InputUtils.getStringInput("Digite a senha do usuário"));

            ServiceContext.getInstance().setUsuario(usuario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }),

    // ADMIN MENU
    ADMIN_TO_HOME(MenuLevel.ADMIN, 1, "Voltar para a home", false,() -> {
        ServiceContext.getInstance().setMenuLevel(MenuLevel.HOME);
    }),
    INIT_DATABASE(MenuLevel.ADMIN, 2, "Inicializar banco de dados", false, () -> {
        DbManager dbManager = new DbManager();
        dbManager.inicializarBanco();
    }),
    RESET_DATABASE(MenuLevel.ADMIN, 3, "Resetar banco de dados", false, () -> {
        DbManager dbManager = new DbManager();
        dbManager.resetarBanco();
    }),
    LIST_USERS(MenuLevel.ADMIN, 4, "Listar Usuários", false,() -> {
        UsuarioService usuarioService = new UsuarioService();
        var result = usuarioService.listarTodos();
        for (int i = 0; i < result.size(); i++) {
            System.out.println(i + 1 + " - " + result.get(i));
        }
    }),
    DELETE_USER(MenuLevel.ADMIN, 5, "Deletar Usuário", false,() -> {
        UsuarioService usuarioService = new UsuarioService();
        usuarioService.deletarUsuario(InputUtils.getLongInput("Digite o ID do usuário para deletar"));
    }),

    // USER MENU
    USER_TO_HOME(MenuLevel.USER, 1, "Voltar para a Home", () -> {
        ServiceContext.getInstance().setMenuLevel(MenuLevel.HOME);
    }),
    UPDATE_USER(MenuLevel.USER, 2, "Atualizar Usuário", () -> {
        UsuarioService usuarioService = new UsuarioService();
        ServiceContext serviceContext = ServiceContext.getInstance();
        Optional<Usuario> user = serviceContext.getUsuario();
        if (user.isEmpty()) {
            MenuOption.USER_TO_HOME.getAction().execute();
            return;
        }

        try {
            usuarioService.atualizarUsuario(user.get().getId(),
                    InputUtils.getStringInput("Digite o novo nome do usuário"),
                    InputUtils.getStringInput("Digite o novo email do usuário"),
                    InputUtils.getStringInput("Digite a nova senha do usuário"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }),

    // RENEWABLE SOURCE MENU
    RENEWABLE_SOURCE_TO_HOME(MenuLevel.RENEWABLE_SOURCE, 1, "Voltar para a Home", () -> {
        ServiceContext.getInstance().setMenuLevel(MenuLevel.HOME);
    }),
    ADD_RENEWABLE_SOURCE(MenuLevel.RENEWABLE_SOURCE, 2, "Adicionar nova Fonte de Energia Renovável", () -> {
        FonteRenovavelService service = new FonteRenovavelService();
        service.criarFonteRenovavel(
                InputUtils.getStringInput("Digite o nome da fonte"),
                InputUtils.getStringInput("Digite a descrição da fonte"));
    }),
    LIST_RENEWABLE_SOURCE(MenuLevel.RENEWABLE_SOURCE, 3, "Listar Fontes de Energia Renovável", () -> {
        FonteRenovavelService service = new FonteRenovavelService();
        List<FonteRenovavel> result = service.listarTodas();
        for (int i = 0; i < result.size(); i++) {
            System.out.println(i + 1 + " - " + result.get(i));
        }
    }),
    SEARCH_RENEWABLE_SOURCE(MenuLevel.RENEWABLE_SOURCE, 4, "Buscar Fonte de Energia Renovável", () -> {
        FonteRenovavelService service = new FonteRenovavelService();
        Optional<FonteRenovavel> result = service.buscarPorId(InputUtils.getLongInput("Digite o ID da fonte"));
        if (result.isEmpty()) {
            System.out.println("Fonte não encontrada");
        } else {
            System.out.println(result.get());
        }
    }),
    UPDATE_RENEWABLE_SOURCE(MenuLevel.RENEWABLE_SOURCE, 5, "Editar Fonte de Energia Renovável", () -> {
        FonteRenovavelService service = new FonteRenovavelService();
        try {
            service.atualizarFonte(
                    InputUtils.getLongInput("Digite o ID da fonte"),
                    InputUtils.getStringInput("Digite o novo Nome da fonte"),
                    InputUtils.getStringInput("Digite a nova Descrição da fonte"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }),
    DELETE_RENEWABLE_SOURCE(MenuLevel.RENEWABLE_SOURCE, 6, "Deletar Fonte de Energia Renovável", () -> {
        FonteRenovavelService service = new FonteRenovavelService();
        try {
            service.deletarFonte(InputUtils.getLongInput("Digite o ID da fonte"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }),

    // ENERGY PROJECT MENU
    ENERGY_PROJECT_TO_HOME(MenuLevel.ENERGY_PROJECT, 1, "Voltar para a Home", () -> {
        ServiceContext.getInstance().setMenuLevel(MenuLevel.HOME);
    }),
    ADD_ENERGY_PROJECT(MenuLevel.ENERGY_PROJECT, 2, "Adicionar novo Projeto", () -> {
        ProjetoEnergiaService service = new ProjetoEnergiaService();
        try {
            service.criarProjeto(
                    InputUtils.getStringInput("Digite o Nome do Projeto"),
                    InputUtils.getStringInput("Digite a Localização do Projeto"),
                    InputUtils.getDoubleInput("Digite a Capacidade do Projeto"),
                    InputUtils.getLongInput("Digite o ID da Energia Renovável"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }),
    LIST_ENERGY_PROJECT(MenuLevel.ENERGY_PROJECT, 3, "Listar Projetos de Energia", () -> {
        ProjetoEnergiaService service = new ProjetoEnergiaService();
        List<ProjetoEnergia> result = service.listarTodos();
        for (int i = 0; i < result.size(); i++) {
            System.out.println(i + 1 + " - " + result.get(i));
        }
    }),
    SEARCH_ENERGY_PROJECT(MenuLevel.ENERGY_PROJECT, 4, "Buscar Projeto de Energia", () -> {
        ProjetoEnergiaService service = new ProjetoEnergiaService();
        Optional<ProjetoEnergia> result = service.buscarPorId(InputUtils.getLongInput("Digite o ID do Projeto"));
        if (result.isEmpty()) {
            System.out.println("Projeto não encontrado");
        } else {
            System.out.println(result.get());
        }
    }),
    UPDATE_ENERGY_PROJECT(MenuLevel.ENERGY_PROJECT, 5, "Editar Projeto de Energia", () -> {
        ProjetoEnergiaService service = new ProjetoEnergiaService();
        try {
            service.atualizarProjeto(
                    InputUtils.getLongInput("Digite o ID do projeto"),
                    InputUtils.getStringInput("Digite o novo Nome do projeto"),
                    InputUtils.getStringInput("Digite a nova Localização do projeto"),
                    InputUtils.getDoubleInput("Digite a nova Capacidade do projeto"),
                    InputUtils.getLongInput("Digite o ID da nova Fonte de Energia"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }),
    DELETE_ENERGY_PROJECT(MenuLevel.ENERGY_PROJECT, 6, "Deletar Fonte de Energia Renovável", () -> {
        FonteRenovavelService service = new FonteRenovavelService();
        try {
            service.deletarFonte(InputUtils.getLongInput("Digite o ID da fonte"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }),
    // INVESTIMENT MENU
    INVESTIMENT_TO_HOME(MenuLevel.INVESTIMENT, 1, "Voltar para a Home", () -> {
        ServiceContext.getInstance().setMenuLevel(MenuLevel.HOME);
    }),
    ADD_INVESTIMENT(MenuLevel.INVESTIMENT, 2, "Adicionar Investimento", () -> {
        InvestimentoService service = new InvestimentoService();
        ServiceContext context = ServiceContext.getInstance();

        Optional<Usuario> usuario = context.getUsuario();
        if (usuario.isEmpty()) {
            System.out.println("Usuário não autenticado.");
            return;
        }

        try {
            service.criarInvestimento(
                    usuario.get().getId(),
                    InputUtils.getLongInput("Digite o ID do Projeto de Energia"),
                    InputUtils.getBigDecimalInput("Digite o valor do investimento"));
            System.out.println("Investimento realizado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }),
    LIST_INVESTIMENT(MenuLevel.INVESTIMENT, 3, "Listar Investimentos", () -> {
        InvestimentoService service = new InvestimentoService();
        List<Investimento> investimentos = service.listarTodos();
        for (int i = 0; i < investimentos.size(); i++) {
            System.out.println((i + 1) + " - " + investimentos.get(i));
        }
    }),
    SEARCH_INVESTIMENT(MenuLevel.INVESTIMENT, 4, "Buscar Investimento", () -> {
        InvestimentoService service = new InvestimentoService();
        Optional<Investimento> investimento = service
                .buscarPorId(InputUtils.getLongInput("Digite o ID do Investimento"));
        if (investimento.isEmpty()) {
            System.out.println("Investimento não encontrado.");
        } else {
            System.out.println(investimento.get());
        }
    }),
    UPDATE_INVESTIMENT(MenuLevel.INVESTIMENT, 5, "Editar Investimento", () -> {
        InvestimentoService service = new InvestimentoService();
        try {
            service.atualizarInvestimento(
                    InputUtils.getLongInput("Digite o ID do Investimento"),
                    InputUtils.getBigDecimalInput("Digite o novo valor do investimento"));
            System.out.println("Investimento atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }),
    DELETE_INVESTIMENT(MenuLevel.INVESTIMENT, 6, "Deletar Investimento", () -> {
        InvestimentoService service = new InvestimentoService();
        service.deletarInvestimento(InputUtils.getLongInput("Digite o ID do Investimento"));
        System.out.println("Investimento deletado com sucesso!");
    }),

    // ENERGY DATA MENU
    ENERGY_DATA_TO_HOME(MenuLevel.ENERGY_DATA, 1, "Voltar para a Home", () -> {
        ServiceContext.getInstance().setMenuLevel(MenuLevel.HOME);
    }),
    ADD_ENERGY_DATA(MenuLevel.ENERGY_DATA, 2, "Adicionar Dado de Energia", () -> {
        DadoEnergiaService service = new DadoEnergiaService();
        try {
            service.criarDadoEnergia(
                    InputUtils.getLongInput("Digite o ID do Projeto de Energia"),
                    InputUtils.getDoubleInput("Digite a energia gerada (kWh)"),
                    LocalDate.now());
            System.out.println("Dado de energia adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }),
    LIST_ENERGY_DATA(MenuLevel.ENERGY_DATA, 3, "Listar Dados de Energia", () -> {
        DadoEnergiaService service = new DadoEnergiaService();
        List<DadoEnergia> dados = service.listarTodos();
        for (int i = 0; i < dados.size(); i++) {
            System.out.println((i + 1) + " - " + dados.get(i));
        }
    }),
    SEARCH_ENERGY_DATA(MenuLevel.ENERGY_DATA, 4, "Buscar Dado de Energia", () -> {
        DadoEnergiaService service = new DadoEnergiaService();
        Optional<DadoEnergia> dado = service.buscarPorId(InputUtils.getLongInput("Digite o ID do Dado de Energia"));
        if (dado.isEmpty()) {
            System.out.println("Dado de energia não encontrado.");
        } else {
            System.out.println(dado.get());
        }
    }),
    UPDATE_ENERGY_DATA(MenuLevel.ENERGY_DATA, 5, "Editar Dado de Energia", () -> {
        DadoEnergiaService service = new DadoEnergiaService();
        try {
            service.atualizarDadoEnergia(
                    InputUtils.getLongInput("Digite o ID do Dado de Energia"),
                    InputUtils.getLongInput("Digite o novo ID do Projeto de Energia"),
                    InputUtils.getDoubleInput("Digite a nova energia gerada (kWh)"),
                    LocalDate.now());
            System.out.println("Dado de energia atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }),
    DELETE_ENERGY_DATA(MenuLevel.ENERGY_DATA, 6, "Deletar Dado de Energia", () -> {
        DadoEnergiaService service = new DadoEnergiaService();
        service.deletarDadoEnergia(InputUtils.getLongInput("Digite o ID do Dado de Energia"));
        System.out.println("Dado de energia deletado com sucesso!");
    }),

    ;

    private final MenuLevel level;
    private final Integer value;
    private final String label;
    private final MenuAction action;
    private final Boolean requiresAuth;

    private MenuOption(MenuLevel level, Integer value, String label, MenuAction action) {
        this.level = level;
        this.value = value;
        this.label = label;
        this.action = action;
        this.requiresAuth = true;
    }

    private MenuOption(MenuLevel level, Integer value, String label, Boolean requiresAuth, MenuAction action) {
        this.level = level;
        this.value = value;
        this.label = label;
        this.action = action;
        this.requiresAuth = requiresAuth;
    }

    public static List<MenuOption> getAllByLevel(MenuLevel level) {
        boolean isAuthenticated = ServiceContext.getInstance().isLoggedIn();
        return Arrays.stream(MenuOption.values()).filter(option -> {
            if (!option.level.equals(level)) {
                return false;
            };

            if (MenuLevel.BASE.equals(level)) {
                return true;
            }

            if (option.requiresAuth && !isAuthenticated) {
                return false;
            }

            if (!option.requiresAuth && isAuthenticated) {
                return false;
            }

            return true;
        }).toList();
    }

    public static Optional<MenuOption> getByLevelAndValue(MenuLevel level, Integer value) {
        boolean isAuthenticated = ServiceContext.getInstance().isLoggedIn();
        return Arrays.stream(MenuOption.values()).filter(option -> {
            if (!option.level.equals(level)) {
                return false;
            };

            if (option.value != value) {
                return false;
            }

            if (MenuLevel.BASE.equals(level)) {
                return true;
            }

            if (option.requiresAuth && !isAuthenticated) {
                return false;
            }

            if (!option.requiresAuth && isAuthenticated) {
                return false;
            }

            return true;
        }).findFirst();
    }

    public String getLabel() {
        return label;
    }

    public MenuAction getAction() {
        return action;
    }

    public Integer getValue() {
        return value;
    }
}
