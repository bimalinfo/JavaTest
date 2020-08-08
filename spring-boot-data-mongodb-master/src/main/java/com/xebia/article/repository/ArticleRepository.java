package com.xebia.article.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xebia.article.model.Article;

public interface ArticleRepository extends MongoRepository<Article, String> {
  List<Article> findById(int id);
  List<Article> findByTitleContaining(String title);
}
