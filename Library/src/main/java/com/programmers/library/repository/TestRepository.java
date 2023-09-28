package com.programmers.library.repository;

import com.programmers.library.domain.Book;
import com.programmers.library.domain.BookStatus;

import java.util.*;
import java.util.stream.Collectors;

public class TestRepository implements Repository{

    private final Map<Long, Book> map;

    public TestRepository() {
        this.map = new HashMap<>();
    }

    @Override
    public void register(Book book) {
        map.put(book.getBookId(), book);
    }

    @Override
    public List<Book> findAllBooks(){
        return new ArrayList<>(map.values());
    }

    @Override
    public void deleteBook(Long id){
        map.remove(id);
    }

    @Override
    public List<Book> findBooksByTitle(String title){
        return map.values().stream().filter(book -> book.getTitle().contains(title)).collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void updateStatus(Book book, BookStatus bookStatus) {
        book.updateBookStatus(bookStatus);
    }

    @Override
    public Long findLastId() {
        Long maxId = map.keySet().stream().max(Long::compareTo).orElse(0L);
        return maxId;
    }
}