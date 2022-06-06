package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

public class Program {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        System.out.println();

        System.out.println(usersRepository.findAll());
        System.out.println(usersRepository.findById(1L));
        System.out.println(usersRepository.findById(10L));
        System.out.println(usersRepository.findByEmail("noreply@kelly.com"));
        System.out.println(usersRepository.findByEmail("null"));
        usersRepository.update(new User(1L,"HELLO@MIKE.COM"));
        usersRepository.save(new User(8L,"nonono@nono.com"));
        System.out.println(usersRepository.findAll());
        usersRepository.delete(8L);

        System.out.println();
        System.out.println();

        usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        System.out.println(usersRepository.findAll());
        System.out.println(usersRepository.findById(1L));
        System.out.println(usersRepository.findById(10L));
        System.out.println(usersRepository.findByEmail("noreply@kelly.com"));
        System.out.println(usersRepository.findByEmail("null"));
        usersRepository.update(new User(2L,"INFO@JACK.COM"));
        usersRepository.save(new User(8L,"nonono@nono.com"));
        System.out.println(usersRepository.findAll());
        usersRepository.delete(8L);

        System.out.println();

    }
}
