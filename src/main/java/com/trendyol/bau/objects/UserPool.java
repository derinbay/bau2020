package com.trendyol.bau.objects;

public class UserPool {

    public static User buyer() {
        return new User("automatedbuyer-b9ffb7da-2d72-4fd4-b41c-f7be3aa4dea6@trendyol.com", "1234qwe");
    }

    public static User buyerWithIncorrectPassword() {
        return new User("automatedbuyer-b9ffb7da-2d72-4fd4-b41c-f7be3aa4dea6@trendyol.com", "dasdas");
    }

    public static User buyerWithIncorrectEmail() {
        return new User("automatedbuyer-b9ffb7da-2d72-4fd4-b41c-f7bedasdasdasas3aa4dea6@trendyol.com", "1234qwe");
    }

    public static User buyerWithEmptyPassword() {
        return new User("automatedbuyer-b9ffb7da-2d72-4fd4-b41c-f7be3aa4dea6@trendyol.com", "");
    }

    public static User buyerWithEmptyEmail() {
        return new User("", "");
    }

    public static User buyerWithInvalidatedEmail() {
        return new User("automatedbuyer", "1234qwe");
    }
}
