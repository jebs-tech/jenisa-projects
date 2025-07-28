package burhanfess.displays;
import burhanfess.users.Cosmic;
import burhanfess.services.CosmicService;
import burhanfess.services.CosmicServiceImpl;
import burhanfess.menfess.Menfess;
import burhanfess.exceptions.UserNotFoundException;
import burhanfess.exceptions.SamePasswordException;
import burhanfess.exceptions.InvalidInputException;
import java.util.List;

public class CosmicDisplay implements Display {
    private Cosmic cosmic;
    private CosmicService cosmicService;
    
    public CosmicDisplay(Cosmic cosmic) {
        this.cosmic = cosmic;
        this.cosmicService = new CosmicServiceImpl(cosmic);
    }
    
    @Override
    public void showHeader() {
        System.out.println("Halo, Cosmic " + cosmic.getUsername() + "!");
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
        System.out.println("1. Mengirim satu menfess");
        System.out.println("2. Melihat menfess publik");
        System.out.println("3. Melihat menfess yang kamu kirim");
        System.out.println("4. Melihat menfess yang kamu terima");
        System.out.println("5. Ubah password");
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
                        sendMenfess();
                        break;
                    case 2:
                        viewUnhiddenMenfesses();
                        break;
                    case 3:
                        viewSentMenfesses();
                        break;
                    case 4:
                        viewReceivedMenfesses();
                        break;
                    case 5:
                        changePassword();
                        break;
                    case 6:
                        logout();
                        return;
                    default:
                        throw new InvalidInputException("Input tidak valid. Silakan coba lagi.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                System.out.println();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }
    
    public void sendMenfess() {
        System.out.println();
        System.out.print("Masukkan isi menfess yang ingin kamu kirim: ");
        String content = scanner.nextLine().trim();
        
        System.out.println("Silakan pilih tipe menfess yang ingin dikirim.");
        System.out.println("1. Curhat");
        System.out.println("2. Promosi");
        System.out.println("3. Confession");
        System.out.print("Masukkan tipe menfess: ");
        
        try {
            String typeInput = scanner.nextLine().trim();
            int type = Integer.parseInt(typeInput);
            
            switch (type) {
                case 1:
                    cosmicService.sendCurhatFess(content);
                    System.out.println("Menfess berhasil dikirim.");
                    break;
                case 2:
                    cosmicService.sendPromosiFess(content);
                    System.out.println("Menfess berhasil dikirim.");
                    break;
                case 3:
                    System.out.print("Masukkan username yang ingin kamu kirimkan menfess: ");
                    String receiverUsername = scanner.nextLine().trim();
                    cosmicService.sendConfessFess(content, receiverUsername);
                    System.out.println("Menfess berhasil dikirim.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Pilihan tidak valid. Silakan coba lagi.");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }
    
    public void viewUnhiddenMenfesses() {
        System.out.println();
        System.out.println("Daftar menfess bersifat publik:");
        List<Menfess> menfesses = cosmicService.getAllUnhiddenMenfesses();
        
        if (menfesses.isEmpty()) {
            System.out.println("Tidak ada menfess publik.");
        } else {
            for (Menfess menfess : menfesses) {
                System.out.println(menfess.toString());
                System.out.println();
            }
        }
        System.out.println();
    }
    
    public void viewSentMenfesses() {
        System.out.println();
        System.out.println("Daftar menfess yang kamu kirim:");
        List<Menfess> menfesses = cosmicService.getAllSentMenfesses();
        
        if (menfesses.isEmpty()) {
            System.out.println("Kamu belum mengirim menfess apapun.");
        } else {
            for (Menfess menfess : menfesses) {
                System.out.println(menfess.toString());
                System.out.println();
            }
        }
        System.out.println();
    }
    
    public void viewReceivedMenfesses() {
        System.out.println();
        System.out.println("Daftar menfess yang kamu terima:");
        List<Menfess> menfesses = cosmicService.getAllReceivedMenfesses();
        
        if (menfesses.isEmpty()) {
            System.out.println("Kamu belum menerima menfess apapun.");
        } else {
            for (Menfess menfess : menfesses) {
                System.out.println(menfess.toString());
                System.out.println();
            }
        }
        System.out.println();
    }
    
    public void changePassword() {
        System.out.println();
        System.out.print("Masukkan password baru: ");
        String newPassword = scanner.nextLine().trim();
        
        try {
            cosmicService.changePassword(newPassword);
            System.out.println("Password berhasil diubah.");
        } catch (SamePasswordException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }
    
    public void logout() {
        System.out.println();
        System.out.println("Kamu telah berhasil logout.");
        showFooter();
        cosmicService.logout();
    }
    
    // Getters and Setters
    public Cosmic getCosmic() { return cosmic; }
    public void setCosmic(Cosmic cosmic) { this.cosmic = cosmic; }
    
    public CosmicService getCosmicService() { return cosmicService; }
    public void setCosmicService(CosmicService cosmicService) { this.cosmicService = cosmicService; }
}