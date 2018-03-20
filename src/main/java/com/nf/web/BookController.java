package com.nf.web;

import com.nf.dao.AuthorDAO;
import com.nf.dao.BookDAO;
import com.nf.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController{
        @Autowired
        private BookDAO bookDAO;

        @Autowired
        private AuthorDAO authorDAO;

        private int page=0;
        private int size=8;
        private int pagination=5;
        private int startpage=page+1;
        private int endpage=page+pagination;
        private int active=0;
        private List list;

    @GetMapping("/index")
    public String index(Model model){
        list=getPage();
        Pageable pageable = new PageRequest(page,size);
        model.addAttribute("books",bookDAO.findAll(pageable));
        model.addAttribute("pages",list);
        model.addAttribute("active",active);
        return "book/index";
    }

    @GetMapping("/add")
    public String add(Book book,Model model){
        model.addAttribute("authors", authorDAO.findAll());
        return "book/add";
    }

    @PostMapping("/add")
    public String save(@Valid Book book, BindingResult errors, Model model, RedirectAttributesModelMap flash) {
        bookDAO.save(book);
        page=(int)Math.ceil(bookDAO.count()/(double)size)-1;
        pageUtil();
        return "redirect:bookinfo";
    }

    @GetMapping("/edit")
    public String toEdie(Model model,Long id){
        model.addAttribute("book",bookDAO.getOne(id));
        model.addAttribute("authors",authorDAO.findAll());
        return "book/update";
    }

    @PostMapping("/update")
    public String edit(@Valid Book book, BindingResult result, Model model,RedirectAttributesModelMap flash) {
        if(invalidBook(book, result, model)) {
            return "book/update";
        }
        bookDAO.save(book);
        flash.addFlashAttribute("msg", "修改成功!");
        flash.addFlashAttribute("msgType", "success");
        return "redirect:index";
    }

    private boolean invalidBook(Book book, Errors result, Model model) {
        if (book.getAuthor() == null || book.getAuthor().getId() < 1) {
            result.rejectValue("author", null, "您需要填写作者的信息哦!");
        }
        if(result.hasErrors()) {
            model.addAttribute("authors",authorDAO.findAll());
        }
        return result.hasErrors();
    }

    @GetMapping("/delete")
    public String delete(Long id,RedirectAttributesModelMap flash){
        bookDAO.delete(id);
        flash.addFlashAttribute("msg", "删除成功!");
        flash.addFlashAttribute("msgType", "success");
        if(page>=Math.ceil(bookDAO.count()/(double)size)-1){
            page=(int)Math.ceil(bookDAO.count()/(double)size)-1;
        }
        pageUtil();
        return "redirect:index";
    }

    @GetMapping("/up")
    public String up(Model model){
        page=page-1;
        if(page<=0){
            page=0;
            active=0;
        }
        if(page>(pagination/2)-1&&page<Math.ceil(bookDAO.count()/(double)size)-(pagination/2)-1){
            endpage = endpage - 1;
            startpage = startpage - 1;
        }else{
            if(active>0){
                active=active-1;
            }
        }

        getPage();
        Pageable pageable = new PageRequest(page,size);
        model.addAttribute("books",bookDAO.findAll(pageable));
        model.addAttribute("pages",list);
        model.addAttribute("active",active);
        return "redirect:index";
    }

    @GetMapping("/down")
    public String down(){
        page=page+1;
        if(page>=Math.ceil(bookDAO.count()/(double)size)-1){
            page=(int)Math.ceil(bookDAO.count()/(double)size)-1;
        }
        if(pagination<Math.ceil(bookDAO.count()/(double)size)){
            if(page>(pagination/2)&&page<Math.ceil(bookDAO.count()/(double)size)-(pagination/2)){
                endpage = endpage + 1;
                startpage = startpage + 1;
            }else{
                if(active<pagination-1){
                    active=active+1;
                }
            }
        }else{
            active=active+1;
            if(active>(int) Math.ceil(bookDAO.count() / (double) size) - 1) {
                active = (int) Math.ceil(bookDAO.count() / (double) size) - 1;
            }
        }
        return "redirect:index";
    }

    @GetMapping("/paging")
    public String paging(int page){
        this.page=page-1;
        if(this.page>=0&&this.page<=(pagination/2)){
            startpage=1;
            endpage=startpage+pagination-1;
            active=this.page-startpage+1;
        }else if(this.page>=Math.ceil(bookDAO.count()/(double)size)-(pagination/2)&&this.page<=Math.ceil(bookDAO.count()/(double)size)){
            endpage=(int)Math.ceil(bookDAO.count()/(double)size);
            startpage=endpage-pagination+1;
            active=this.page-startpage+1;
        }else{
            startpage=(this.page-pagination/2)+1;
            endpage=startpage+pagination-1;
            active=this.page-startpage+1;
        }
        return "redirect:index";
    }

    public List getPage(){
        List list=new ArrayList();
        if(pagination<Math.ceil(bookDAO.count()/(double)size)){
            for(int i=startpage;i<=endpage;i++){
                list.add(i);
            }
        }else{
            for(int i=1;i<=Math.ceil(bookDAO.count()/(double)size);i++){
                list.add(i);
            }
        }
        return list;
    }

    public void pageUtil(){
        if(page>=0&&page<=(pagination/2)){
            startpage=1;
            endpage=startpage+pagination-1;
            active=page-startpage+1;
        }else if(page>=Math.ceil(bookDAO.count()/(double)size)-(pagination/2)&&page<=Math.ceil(bookDAO.count()/(double)size)){
            endpage=(int)Math.ceil(bookDAO.count()/(double)size);
            startpage=endpage-pagination+1;
            active=page-startpage+1;
        }else{
            startpage=(page-pagination/2)+1;
            endpage=startpage+pagination-1;
            active=page-startpage+1;
        }
    }
}
