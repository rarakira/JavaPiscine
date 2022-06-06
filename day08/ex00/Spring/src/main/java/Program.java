import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import printer.Printer;

/*
* Printer options:
* printerWithPrefix-ErrUpper
* printerWithPrefix-ErrLower
* printerWithDateTime-ErrUpper
* printerWithDateTime-ErrLower
* printerWithPrefix-StdUpper
* printerWithPrefix-StdLower
* printerWithDateTime-StdUpper
* printerWithDateTime-StdLower
*/
public class Program {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean("printerWithPrefix-ErrUpper", Printer.class);
        printer.print("HoW ArE YoU?");
        printer.print("World!");
    }
}
