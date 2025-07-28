package burhanfess.displays;
import burhanfess.users.Admin;
import burhanfess.users.User;
import burhanfess.users.comparators.UserIdComparator;
import burhanfess.users.comparators.UserUsernameComparator;
import burhanfess.services.AdminService;
import burhanfess.services.AdminServiceImpl;
import burhanfess.menfess.Menfess;
import burhanfess.exceptions.*;
import java.util.List;
import java.util.Comparator;

public class AdminDisplay implements Display {
    private Admin admin;
    private AdminService adminService;
    
    public AdminDisplay(Admin admin) {
        this.admin = admin;
        this.adminService = new AdminServiceImpl();
    }
    
    @Override
    public void showHeader() {
        System.out.println("Halo, Admin " + admin.getUsername() + "!");
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
        System.out.println("Silakan pilih salah satu opsi berikut");
        System.out.println("1. Lihat daftar pengguna");
        System.out.println("2. Tambah admin");
        System.out.println("3. Reset password pengguna");
        System.out.println("4. Sembunyikan menfess");
        System.out.println("5. Tampilkan menfess");
        System.out.println("6. Logout");
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
                        showAllUsers();
                        break;
                    case 2:
                        addAdmin();
                        break;
                    case 3:
                        resetPassword();
                        break;
                    case 4:
                        hideMenfess();
                        break;
                    case 5:
                        unhideMenfess();
                        break;
                    case 6:
                        logout();
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
    
    public void showAllUsers() {
        System.out.println();
        System.out.println("Silakan pilih opsi pengurutan:");
        System.out.println("1. Berdasarkan ID");
        System.out.println("2. Berdasarkan username");
        System.out.print("Masukkan pilihan: ");
        
        try {
            String input = scanner.nextLine().trim();
            int choice = Integer.parseInt(input);
            
            Comparator<User> comparator;
            switch (choice) {
                case 1:
                    comparator = new UserIdComparator();
                    break;
                case 2:
                    comparator = new UserUsernameComparator();
                    break;
                default:
                    throw new InvalidInputException("Input tidak valid. Silakan coba lagi.");
            }
            
            List<User> users = adminService.getAllUsers(comparator);
            System.out.println();
            
            if (choice == 1) {
                System.out.println("Daftar pengguna diurutkan berdasarkan ID:");
            } else {
                System.out.println("Daftar pengguna diurutkan berdasarkan username:");
            }
            
            for (User user : users) {
                System.out.println("ID: " + user.getId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Role: " + user.getRole());
                System.out.println();
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Silakan masukkan angka yang sesuai.");
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }
    
    public void addAdmin() {
        System.out.println();
        System.out.println("Silakan masukkan username dan password untuk admin baru:");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        
        try {
            adminService.addAdmin(username, password);
            System.out.println("Admin berhasil ditambahkan.");
        } catch (UsernameAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }
    
    public void resetPassword() {
        System.out.println();
        System.out.print("Masukkan username pengguna yang akan direset passwordnya: ");
        String username = scanner.nextLine().trim();
        System.out.print("Masukkan password baru: ");
        String newPassword = scanner.nextLine().trim();
        
        try {
            adminService.resetPassword(username, newPassword);
            System.out.println("Password berhasil direset.");
        } catch (UserNotFoundException | SamePasswordException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }
    
    public void hideMenfess() {
        System.out.println();
        System.out.println("Daftar menfess yang ditampilkan:");
        List<Menfess> menfesses = adminService.getAllUnhiddenMenfesses();
        
        if (menfesses.isEmpty()) {
            System.out.println("Tidak ada menfess yang ditampilkan.");
            System.out.println();
            return;
        }
        
        for (Menfess menfess : menfesses) {
            System.out.println(menfess.toString());
            System.out.println();
        }
        
        System.out.print("Masukkan ID menfess yang ingin disembunyikan: ");
        try {
            String input = scanner.nextLine().trim();
            int menfessId = Integer.parseInt(input);
            
            adminService.hideMenfess(menfessId);
            System.out.println("Menfess berhasil disembunyikan.");
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Silakan masukkan angka yang sesuai.");
        } catch (MenfessNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }
    
    public void unhideMenfess() {
        System.out.println();
        System.out.println("Daftar menfess yang disembunyikan:");
        List<Menfess> menfesses = adminService.getAllHiddenMenfesses();
        
        if (menfesses.isEmpty()) {
            System.out.println("Tidak ada menfess yang disembunyikan.");
            System.out.println();
            return;
        }
        
        for (Menfess menfess : menfesses) {
            System.out.println(menfess.toString());
            System.out.println();
        }
        
        System.out.print("Masukkan ID menfess yang ingin ditampilkan: ");
        try {
            String input = scanner.nextLine().trim();
            int menfessId = Integer.parseInt(input);
            
            adminService.unhideMenfess(menfessId);
            System.out.println("Menfess berhasil ditampilkan.");
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Silakan masukkan angka yang sesuai.");
        } catch (MenfessNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }
    
    public void logout() {
        System.out.println();
        System.out.println("Kamu telah berhasil logout.");
        showFooter();
        adminService.logout();
    }
    
    // Getters and Setters
    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }
    
    public AdminService getAdminService() { return adminService; }
    public void setAdminService(AdminService adminService) { this.adminService = adminService; }
}