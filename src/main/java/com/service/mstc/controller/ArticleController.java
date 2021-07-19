package com.service.mstc.controller;

import com.service.mstc.dto.PageStatusDto;
import com.service.mstc.dto.common.DataListDto;
import com.service.mstc.dto.common.PageableDto;
import com.service.mstc.model.Article;
import com.service.mstc.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

  @Autowired
  private ArticleService articleService;

  @GetMapping
  public DataListDto get(PageStatusDto pageableDto){
    return articleService.get(pageableDto);
  }

  @GetMapping("/{id}")
  public Article getById(@PathVariable String id){
    return articleService.getById(id);
  }

  @GetMapping("/slug/{slug}")
  public Article getBySlug(@PathVariable String slug){
    return articleService.getBySlug(slug);
  }

  @PostMapping
  public Article post(@RequestBody Article article){
    return articleService.save(article);
  }

  @PutMapping
  public Article update(@RequestBody Article article){
    return articleService.update(article);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id){
    articleService.delete(id);
  }
}
