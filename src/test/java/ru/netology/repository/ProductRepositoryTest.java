package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Book coreJava = new Book();

    @Test
    public void shouldNotThrowExceptionIfTryRemoveExistElement() {
        repository = new ProductRepository();
        repository.save(coreJava);

        assertEquals(coreJava, repository.findById(0));

        repository.removeById(0);

        assertEquals(null, repository.findById(0));
    }

    @Test
    public void shouldThrowExceptionIfTryRemoveNotExistElement() {
        repository = new ProductRepository();

        try {
            repository.removeById(10);
        }catch (NotFoundException exception)
        {
            String expected = "Product not found: id=10";
            String actual = exception.getMessage();

            assertEquals(expected, actual);
        }
    }

    @Test
    public void shouldSaveOneItem() {
        repository.save(coreJava);

        Product[] expected = new Product[]{coreJava};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
}
