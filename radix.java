import java.io.BufferedReader;
import java.io.InputStreamReader;

public class radix{
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Ingresa números separados:");
            String linea = br.readLine(); 
            String[] partes = linea.trim().split("\\s+");
            int[] arr = new int[partes.length];

            for (int i = 0; i < partes.length; i++) {
                arr[i] = Integer.parseInt(partes[i]);
            }
            radixSort(arr);
            System.out.println("Números ordenados (Radix Sort):");
            for (int n : arr) {
                System.out.print(n + " ");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void radixSort(int[] arr) {
        int max = obtenerMax(arr);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }
    public static int obtenerMax(int[] arr) {
        int max = arr[0];
        for (int n : arr) {
            if (n > max) {
                max = n;
            }
        }
        return max;
    }
    public static void countingSort(int[] arr, int exp) {
        int[] salida = new int[arr.length];
        int[] conteo = new int[10]; 
        for (int n : arr) {
            int digito = (n / exp) % 10;
            conteo[digito]++;
        }
        for (int i = 1; i < 10; i++) {
            conteo[i] += conteo[i - 1];
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            int digito = (arr[i] / exp) % 10;
            salida[conteo[digito] - 1] = arr[i];
            conteo[digito]--;
        }
        System.arraycopy(salida, 0, arr, 0, arr.length);
    }
}
