package com.whitelopes.liter.principal;

import com.whitelopes.liter.helper.LanguageMap;
import com.whitelopes.liter.integrations.GutendexIntegration;
import com.whitelopes.liter.models.Book;
import com.whitelopes.liter.service.BooksService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {
    private BooksService _booksService;
    private Scanner scanner = new Scanner(System.in);

    public MenuPrincipal(BooksService bookService) {
        _booksService = bookService;
    }

    public void ShowPrincipalMenu() {
        Integer choice = 0;

        System.out.println("Bem vindo ao Liter.");

        do {
            FirstMenu();
            choice = tryConvertUserChoiceToInt(scanner);
            switch (choice) {
                case 1:
                    consultBook();
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    listLanguagueBooks();
                    break;
                case 4:
                    listAuthors();
                    break;
                case 5:
                    listAuthorsByLivingYear();
                    break;
                case 6:
                    countBooksByLanguage();
                    break;
                case 99:
                    break;
                default:
                    System.out.println("Hum, parece que você não escolheu uma opção válida.");
                    System.out.println("Digite uma das opções do menu abaixo por favor.");
            }
        } while (choice != 99);
    }

    private void FirstMenu() {
        System.out.println("---------------------------------------");
        System.out.println("1 - Consultar Livros no gutendex");
        System.out.println("2 - Listar Livros");
        System.out.println("3 - Listar Livros em uma certa lingua");
        System.out.println("4 - Listar autores");
        System.out.println("5 - Listar autores pelo ano em que vivia");
        System.out.println("6 - Contagem de livros por idioma");
        System.out.println("99 - Sair");
        System.out.println("---------------------------------------");
    }

    private void LanguageMenu() {
        System.out.println("---------------------------------------");
        LanguageMap.languages().forEach((i, s) -> System.out.println(i + " - " + s));
        System.out.println("99 - Sair");
        System.out.println("---------------------------------------");
    }

    private void countBooksByLanguage() {
        Integer choice = 0;
        System.out.println("Selecione a lingua que você quer procurar:");
        LanguageMenu();
        do {

            choice = tryConvertUserChoiceToInt(scanner);

            System.out.println(LanguageMap.languages().size());

            if (choice >= 1 && choice <= LanguageMap.languages().size()) break;

            if (choice == 99) return;

            System.out.println("Por favor escolha uma das opções válidas!");

        } while (choice != 99);

        System.out.println("Existem " + _booksService.CountBooksByLanguage(LanguageMap.languages().get(choice)) + " livros nesse lingua");


    }

    public void listAuthorsByLivingYear() {
        System.out.println("Digite o ano em que deseja procurar:");
        Integer choice = 0;

        do {
            choice = tryConvertUserChoiceToInt(scanner);
        } while (choice == 0);

        var authors = _booksService.FindALlAuthorsByLivingyear(choice, choice);
        authors.forEach(a -> {
            System.out.println("-----------------------------------");
            System.out.println("Nome autor: " + a.name());
            System.out.println("Ano de Nascimento: " + a.birthYear());
            System.out.println("Ano de falecimento: " + a.deathYear());
            System.out.print("Livros:");
            a.books().forEach(b -> {
                System.out.print(" " + b.getTitle());
                System.out.print(a.books().indexOf(b) == a.books().size() - 1 ? "." : ",");
                System.out.println();
            });
            System.out.println("-----------------------------------");

        });
    }

    private void listAuthors() {
        var authors = _booksService.FindAllAuthors();
        authors.forEach(a -> {
            System.out.println("-----------------------------------");
            System.out.println("Nome autor: " + a.name());
            System.out.println("Ano de Nascimento: " + a.birthYear());
            System.out.println("Ano de falecimento: " + a.deathYear());
            System.out.print("Livros:");
            a.books().forEach(b -> {
                System.out.print(" " + b.getTitle());
                System.out.print(a.books().indexOf(b) == a.books().size() - 1 ? "." : ",");
                System.out.println();
            });
            System.out.println("-----------------------------------");

        });
    }

    private void listLanguagueBooks() {
        Integer choice = 0;
        System.out.println("Selecione a lingua que você quer procurar:");
        LanguageMenu();
        do {

            choice = tryConvertUserChoiceToInt(scanner);

            System.out.println(LanguageMap.languages().size());

            if (choice >= 1 && choice <= LanguageMap.languages().size()) break;

            if (choice == 99) return;

            System.out.println("Por favor escolha uma das opções válidas!");

        } while (choice != 99);

        var books = _booksService.findAllByLanguage(LanguageMap.languages().get(choice));
        books.forEach(this::showBookInfo);

    }

    private void listBooks() {
        _booksService.findAll().forEach(this::showBookInfo);
    }

    private void consultBook() {
        System.out.println("Digite o nome do livro ou o nome do autor por favor");
        Integer choice = 0;
        boolean leave = true;
        var bookName = scanner.nextLine();
        var books = GutendexIntegration.ConsultBook(bookName);

        if (books.results().isEmpty()) {
            System.out.println("Não foi encontrando nenhum livro para o título ou nome do autor :" + bookName);
            return;
        }

        if (books.results().size() > 1) {
            System.out.println("O nome de livro ou autor que você passou resultou em " + books.results().size() + " livros.");
            System.out.println("Irei lhe passar a lista deles, por favor escolha um ou digite 99 para sair.");
            books.results()
                    .forEach(b -> System.out.println(books.results().indexOf(b) + " - " +
                            "Título:" + b.title() + " Autores:" + b.authors().get(0).name()));
            System.out.println("99 - SAIR");
            do {
                choice = tryConvertUserChoiceToInt(scanner);

                if (choice == 99) return;

                if (books.results().size() >= choice && choice >= 0) leave = false;

            } while (leave);
        }

        Book book = choice == 0
                ? new Book(books.results().get(0))
                : new Book(books.results().get(choice));

        _booksService.add(book);

    }

    private int tryConvertUserChoiceToInt(Scanner scanner) {
        int userChoice = 0;
        do {
            try {
                userChoice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número.");
            } finally {
                scanner.nextLine();
            }
        } while (userChoice == 0);

        return userChoice;
    }

    private void showBookInfo(Book book) {
        System.out.print("Título:" + book.getTitle()
                + " - Autores:");
        book.getAuthors().forEach(a -> System.out.print(" " + a.getName()));
        System.out.print(" - Idiomas:");
        book.getLanguages().forEach(l -> System.out.print(" " + l));
        System.out.print(" - Número de downloads: " + book.getDownloadCount());
        System.out.println();
    }
}
