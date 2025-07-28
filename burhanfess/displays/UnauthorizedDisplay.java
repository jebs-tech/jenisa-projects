package burhanfess.displays;
import burhanfess.users.User;
import burhanfess.users.Admin;
import burhanfess.users.Cosmic;
import burhanfess.services.UnauthorizedService;
import burhanfess.services.UnauthorizedServiceImpl;
import burhanfess.exceptions.UsernameAlreadyExistsException;
import burhanfess.exceptions.InvalidInputException;

public class UnauthorizedDisplay implements Display {
    private UnauthorizedService unauthorizedService;
    private User loggedInUser;
    
    public UnauthorizedDisplay() {
        this.unauthorizedService = new UnauthorizedServiceImpl();
    }
    
    @Override
    public void showHeader() {
        System.out.println("BurhanFess - 2025");
        System.out.println("Created by Burhan");
        System.out.println();
        System.out.println("// Masuk halaman pengguna dan logout");
        System.out.println();
        System.out.println("Selamat datang di Burhanfess");
        showCurrentDate();
        System.out.println("-----------------------------------------------------------------------");
    }
    
    @Override
    public void showCurrentDate() {
        System.out.println("Sel, 22 Jul 2025");
    }
    
    @Override
    public void showFooter() {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("BurhanFess - 2025");
        System.out.println("Created by Burhan");
    }
    
    @Override
    public void showMenu() {
        showHeader();
        System.out.println("Silakan pilih salah satu opsi berikut.");
        System.out.println("1. Registrasi");
        System.out.println("2. Login");
        System.out.println("3. Keluar");
        System.out.print("Masukkan pilihan: ");
    }
    
    @Override
    public void run() {
        while (true) {
            showMenu();
            
            try {
                String input = scanner.nextLine().trim();
                int choice = Integer.parseInt(input);
                
                switch (choice) {
                    case 1:
                        register();
                        break;
                    case 2:
                        User user = login();
                        if (user != null) {
                            // Switch to appropriate display based on user role
                            if (user instanceof Admin) {
                                AdminDisplay adminDisplay = new AdminDisplay((Admin) user);
                                adminDisplay.run();
                            } else if (user instanceof Cosmic) {
                                CosmicDisplay cosmicDisplay = new CosmicDisplay((Cosmic) user);
                                cosmicDisplay.run();
                            }
                        }
                        break;
                    case 3:
                        exit();
                        return;
                    default:
                        throw new InvalidInputException("Input tidak valid. Silakan coba lagi.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Silakan masukkan angka yang sesuai.");
                System.out.println();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }
    
    public void register() {
        System.out.println();
        System.out.println("Silakan masukkan username dan password untuk registrasi:");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        
        try {
            unauthorizedService.register(username, password);
            System.out.println("Registrasi berhasil! Silakan login.");
        } catch (UsernameAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }
    
    public User login() {
        System.out.println();
        System.out.println("Silakan masukkan username dan password untuk login:");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        
        try {
            User user = unauthorizedService.login(username, password);
            System.out.println("Login berhasil! Selamat datang, " + username + "!");
            System.out.println();
            return user;
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            System.out.println();
            return null;
        }
    }
    
    public void exit() {
        System.out.println();
        System.out.println("Terima kasih telah menggunakan Burhanfess. Sampai jumpa!");
        showFooter();
        System.exit(0);
    }
    
    // Getters and Setters
    public UnauthorizedService getUnauthorizedService() { return unauthorizedService; }
    public void setUnauthorizedService(UnauthorizedService unauthorizedService) { this.unauthorizedService = unauthorizedService; }
}
