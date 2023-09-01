package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Comment;

public interface CommentService {
    Comment registerComment(Comment p) throws Exception;

    Comment updateComment(Comment p) throws Exception;

    void deleteComment(int id) throws Exception;
}
