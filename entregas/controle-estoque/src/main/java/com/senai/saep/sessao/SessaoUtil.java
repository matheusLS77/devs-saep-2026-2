package com.senai.saep.sessao;

import jakarta.servlet.http.HttpSession;

public final class SessaoUtil {
    private static final String USUARIO_LOGADO = "usuarioLogado";

    public SessaoUtil() {
    }

    public static void registrarSessao(HttpSession session, SessaoDto sessaoDto) {
        session.setAttribute(USUARIO_LOGADO, sessaoDto);
    }

    public static SessaoDto obterSessao(HttpSession session) {
        Object usuarioLogado = session.getAttribute(USUARIO_LOGADO);

        if (usuarioLogado == null) {
            return null;
        }

        return (SessaoDto) usuarioLogado;
    }

    public static void encerrarSessao(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
    }
}
