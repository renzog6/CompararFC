package ar.nex.factura;

import java.io.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * FXML Controller class
 *
 * @author Renzo
 */
public class Comprar_facturasController implements Initializable {

    @FXML
    private Tab tabSistema;
    @FXML
    private Button btnSitema;
    @FXML
    private Label fileSistema;
    @FXML
    private Label lblSistemaInfo;
    @FXML
    private TableView<Factura> tblSistema;
    @FXML
    private Tab tabAfip;
    @FXML
    private Button btnAfip;
    @FXML
    private Label fileAfip;
    @FXML
    private Label lblAfipInfo;
    @FXML
    private TableView<Factura> tblAfip;
    @FXML
    private Tab tabResultado;
    @FXML
    private TableView<Factura> tblResultado;

    private List<Factura> listaSistema;
    private ObservableList<Factura> dataSistema = FXCollections.observableArrayList();
    private List<Factura> listaAfip;
    private ObservableList<Factura> dataAfip = FXCollections.observableArrayList();
    private List<Factura> listaResultado;
    private ObservableList<Factura> dataResultado = FXCollections.observableArrayList();
    private FilteredList<Factura> filteredData;

    @FXML
    private TableColumn<?, ?> siDenominacion;
    @FXML
    private TableColumn<?, ?> siCuit;
    @FXML
    private TableColumn<?, ?> siFecha;
    @FXML
    private TableColumn<?, ?> siTipo;
    @FXML
    private TableColumn<?, ?> siLetra;
    @FXML
    private TableColumn<?, ?> siPuntoComprobante;
    @FXML
    private TableColumn<?, ?> siNumeroComprobante;
    @FXML
    private TableColumn<?, ?> siNetoGravado;
    @FXML
    private TableColumn<?, ?> siNetoNoGravado;
    @FXML
    private TableColumn<?, ?> siIVA;
    @FXML
    private TableColumn<?, ?> siTotal;
    @FXML
    private TableColumn<?, ?> siOtro;

    @FXML
    private TableColumn<?, ?> afDenominacion;
    @FXML
    private TableColumn<?, ?> afCuit;
    @FXML
    private TableColumn<?, ?> afFecha;
    @FXML
    private TableColumn<?, ?> afTipo;
    @FXML
    private TableColumn<?, ?> afLetra;
    @FXML
    private TableColumn<?, ?> afPuntoComprobante;
    @FXML
    private TableColumn<?, ?> afNumeroComprobante;
    @FXML
    private TableColumn<?, ?> afNetoGravado;
    @FXML
    private TableColumn<?, ?> afNetoNoGravado;
    @FXML
    private TableColumn<?, ?> afIVA;
    @FXML
    private TableColumn<?, ?> afTotal;
    @FXML
    private TableColumn<?, ?> afOtro;

    @FXML
    private TableColumn<?, ?> rsDenominacion;
    @FXML
    private TableColumn<?, ?> rsCuit;
    @FXML
    private TableColumn<?, ?> rsFecha;
    @FXML
    private TableColumn<?, ?> rsTipo;
    @FXML
    private TableColumn<?, ?> rsLetra;
    @FXML
    private TableColumn<?, ?> rsPuntoComprobante;
    @FXML
    private TableColumn<?, ?> rsNumeroComprobante;
    @FXML
    private TableColumn<?, ?> rsNetoGravado;
    @FXML
    private TableColumn<?, ?> rsNetoNoGravado;
    @FXML
    private TableColumn<?, ?> rsIVA;
    @FXML
    private TableColumn<?, ?> rsTotal;
    @FXML
    private TableColumn<?, ?> rsOtro;
    @FXML
    private TableColumn<?, ?> rsOrigen;
    @FXML
    private TableColumn<?, ?> rsCodigo;

    String pattern = "dd/MM/yyyy";
    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

    @FXML
    private Button btnBoom;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField searchBox;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listaSistema = new ArrayList<>();
        listaAfip = new ArrayList<>();
        listaResultado = new ArrayList<>();
        filteredData = new FilteredList<>(dataResultado);
        //btnSitema.setOnAction(e -> abrirListaSistema());
        //btnAfip.setOnAction(e -> abrirListaAfip());

        btnSitema.setOnAction(e -> openFile(e, 0));
        btnAfip.setOnAction(e -> openFile(e, 1));

        initTableSistema();
        initTableAfip();
        initTableResultado();

        btnBoom.setOnAction(e -> Boom());

        btnGuardar.setOnAction(e -> guardarResultado());

    }

    private void initTableSistema() {
        siDenominacion.setCellValueFactory(new PropertyValueFactory<>("denominacion"));
        siCuit.setCellValueFactory(new PropertyValueFactory<>("cuit"));
        siFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        siTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        siLetra.setCellValueFactory(new PropertyValueFactory<>("letra"));
        siPuntoComprobante.setCellValueFactory(new PropertyValueFactory<>("puntoComprobante"));
        siNumeroComprobante.setCellValueFactory(new PropertyValueFactory<>("numeroComprobante"));
        siNetoGravado.setCellValueFactory(new PropertyValueFactory<>("netoGravado"));
        siNetoNoGravado.setCellValueFactory(new PropertyValueFactory<>("netoNoGravado"));
        siIVA.setCellValueFactory(new PropertyValueFactory<>("IVA"));
        siTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        siOtro.setCellValueFactory(new PropertyValueFactory<>("otroX"));
    }

    private void initTableAfip() {
        afDenominacion.setCellValueFactory(new PropertyValueFactory<>("denominacion"));
        afCuit.setCellValueFactory(new PropertyValueFactory<>("cuit"));
        afFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        afTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        afLetra.setCellValueFactory(new PropertyValueFactory<>("letra"));
        afPuntoComprobante.setCellValueFactory(new PropertyValueFactory<>("puntoComprobante"));
        afNumeroComprobante.setCellValueFactory(new PropertyValueFactory<>("numeroComprobante"));
        afNetoGravado.setCellValueFactory(new PropertyValueFactory<>("netoGravado"));
        afNetoNoGravado.setCellValueFactory(new PropertyValueFactory<>("netoNoGravado"));
        afIVA.setCellValueFactory(new PropertyValueFactory<>("IVA"));
        afTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        afOtro.setCellValueFactory(new PropertyValueFactory<>("otroX"));
    }

    private void initTableResultado() {
        rsDenominacion.setCellValueFactory(new PropertyValueFactory<>("denominacion"));
        rsCuit.setCellValueFactory(new PropertyValueFactory<>("cuit"));
        rsFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        rsTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        rsLetra.setCellValueFactory(new PropertyValueFactory<>("letra"));
        rsPuntoComprobante.setCellValueFactory(new PropertyValueFactory<>("puntoComprobante"));
        rsNumeroComprobante.setCellValueFactory(new PropertyValueFactory<>("numeroComprobante"));
        rsNetoGravado.setCellValueFactory(new PropertyValueFactory<>("netoGravado"));
        rsNetoNoGravado.setCellValueFactory(new PropertyValueFactory<>("netoNoGravado"));
        rsIVA.setCellValueFactory(new PropertyValueFactory<>("IVA"));
        rsTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        rsOtro.setCellValueFactory(new PropertyValueFactory<>("otroX"));
        rsOrigen.setCellValueFactory(new PropertyValueFactory<>("origen"));
        rsCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    }

    private void abrirListaSistema() {
        try {
            // Creating a Workbook from an Excel file (.xls or .xlsx)
            Workbook workbook = WorkbookFactory.create(new File(fileSistema.getText()));
            // Getting the Sheet at index zero
            Sheet sheet = workbook.getSheetAt(0);
            // Create a DataFormatter to format and get each cell's value as String
            DataFormatter dataFormatter = new DataFormatter();

            // 2. Or you can use a for-each loop to iterate over the rows and columns
            System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
            int rowCount = 0;
            double sumaNeto = 0.0, sumaIVA = 0.0, sumaTotal = 0.0;
            for (Row row : sheet) {
                if (rowCount < 6) {
                    rowCount++;
                } else {
                    Factura factura = new Factura("Sistema");

                    if (row.getCell(0).toString().trim().length() > 0) {

                        factura.setDenominacion(row.getCell(2).toString());
                        factura.setCuit(row.getCell(4).toString());

                        String valor = dataFormatter.formatCellValue(row.getCell(1));
                        factura.setFecha(dateFormat.format(new Date(valor)));

                        factura.setTipo(row.getCell(6).toString());
                        factura.setLetra(row.getCell(7).toString());

                        try {
                            factura.setPuntoComprobante(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(8))));
                        } catch (Exception e) {
                            factura.setPuntoComprobante(0);
                        }
                        try {
                            factura.setNumeroComprobante(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(9))));
                        } catch (Exception e) {
                            factura.setNumeroComprobante(0);
                        }
                        try {
                            valor = dataFormatter.formatCellValue(row.getCell(12)).replace(".", "").replace(",", ".").replace("(", "").replace(")", "");
                            factura.setNetoGravado(Double.parseDouble(valor));
                        } catch (Exception e) {
                            factura.setTotal(0.0);
                        }
                        try {
                            valor = dataFormatter.formatCellValue(row.getCell(18)).replace(".", "").replace(",", ".").replace("(", "").replace(")", "");
                            factura.setNetoNoGravado(Double.parseDouble(valor));
                        } catch (Exception e) {
                            factura.setNetoNoGravado(0.0);
                        }
                        try {
                            valor = dataFormatter.formatCellValue(row.getCell(13)).replace(".", "").replace(",", ".").replace("(", "").replace(")", "");
                            factura.setIVA(Double.parseDouble(valor));
                        } catch (Exception e) {
                            factura.setIVA(0.0);
                        }
                        try {
                            valor = dataFormatter.formatCellValue(row.getCell(23)).replace(".", "").replace(",", ".").replace("(", "").replace(")", "");
                            factura.setTotal(Double.parseDouble(valor));
                        } catch (Exception e) {
                            factura.setTotal(0.0);
                        }
                        try {
                            double sumaOtro = 0.0;
                            valor = dataFormatter.formatCellValue(row.getCell(17)).replace(",", "").replace("(", "").replace(")", "");
                            sumaOtro = Double.parseDouble(valor);
                            valor = dataFormatter.formatCellValue(row.getCell(19)).replace(",", "").replace("(", "").replace(")", "");
                            sumaOtro += Double.parseDouble(valor);
                            valor = dataFormatter.formatCellValue(row.getCell(20)).replace(",", "").replace("(", "").replace(")", "");
                            sumaOtro += Double.parseDouble(valor);
                            valor = dataFormatter.formatCellValue(row.getCell(21)).replace(",", "").replace("(", "").replace(")", "");
                            sumaOtro += Double.parseDouble(valor);
                            valor = dataFormatter.formatCellValue(row.getCell(22)).replace(",", "").replace("(", "").replace(")", "");
                            sumaOtro += Double.parseDouble(valor);
                            factura.setOtroX(sumaOtro);
                        } catch (Exception e) {
                            factura.setOtroX(0.0);
                        }

                        sumaNeto += factura.getNetoGravado() + factura.getNetoNoGravado();
                        sumaIVA += factura.getIVA();
                        sumaTotal += factura.getTotal();
                        factura.setCodigo(-1);
                        listaSistema.add(factura);
                    }
                    System.out.println("Lista del Sistema: " + listaSistema.size() + " - " + listaSistema.get(listaSistema.size() - 1));
                }
            }
            // Closing the workbook
            workbook.close();

            dataSistema.addAll(listaSistema);
            tblSistema.setItems(dataSistema);

            lblSistemaInfo.setText("Cantidad Comprobantes:" + listaSistema.size() + " - Total Neto=" + String.format("$%,.2f", sumaNeto) + " - Total IVA=" + String.format("$%,.2f", sumaIVA) + " - TOTAL=" + String.format("$%,.2f", sumaTotal));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void abrirListaAfip() {
        try {
            // Creating a Workbook from an Excel file (.xls or .xlsx)
            Workbook workbook = WorkbookFactory.create(new File(fileAfip.getText()));
            // Getting the Sheet at index zero
            Sheet sheet = workbook.getSheetAt(0);
            // Create a DataFormatter to format and get each cell's value as String
            DataFormatter dataFormatter = new DataFormatter();

            // 2. Or you can use a for-each loop to iterate over the rows and columns
            System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
            int rowCount = 0;
            double sumaNeto = 0.0, sumaIVA = 0.0, sumaTotal = 0.0;
            for (Row row : sheet) {
                if (rowCount < 2) {
                    rowCount++;
                } else {
                    Factura factura = new Factura("AFIP");

                    if (row.getCell(0).toString().trim().length() > 0) {

                        factura.setDenominacion(row.getCell(8).toString());

                        String str = dataFormatter.formatCellValue(row.getCell(7));//row.getCell(7).toString();
                        str = str.substring(0, 2) + "-" + str.substring(2);
                        str = str.substring(0, 11) + "-" + str.substring(11);
                        factura.setCuit(str);

                        str = dataFormatter.formatCellValue(row.getCell(0));
                        // System.out.println("Fecha AFIP>>>" + str);
                        factura.setFecha(str);//dateFormat.format(new Date(str)));

                        str = row.getCell(1).toString();
                        factura.setLetra(str.substring(str.length() - 1));
                        String tipo = "XX";
                        double signo = 1.0;
                        //String codigo = str.substring(0, 3);
                        //String[] parts = str.split("-");
                        //String codigo =  parts[0];
                        String codigo =  str.split("-")[0];
                        System.out.println("CODIGO::: "+codigo);
                        switch (codigo.trim()) {
                            case "1":
                            case "6":
                            case "11":
                            case "201":
                            case "211":
                                tipo = "FC";
                                break;
                            case "2":
                            case "7":
                            case "12":
                            case "202":
                            case "212":
                                tipo = "ND";
                                break;
                            case "3":
                            case "8":
                            case "13":
                            case "203":
                            case "213":
                                tipo = "NC";
                                signo = -1.0;
                                break;
                            default:
                                tipo = "OT";
                                break;
                        }
                        factura.setTipo(tipo);

                        factura.setPuntoComprobante(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(2))));
                        factura.setNumeroComprobante(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(3))));

                        factura.setNetoGravado(setNumerosAfip(row.getCell(11), signo));

                        factura.setNetoNoGravado(setNumerosAfip(row.getCell(12), signo));

                        factura.setIVA(setNumerosAfip(row.getCell(14), signo));

                        factura.setTotal(setNumerosAfip(row.getCell(15), signo));

                        factura.setOtroX(setNumerosAfip(row.getCell(13), signo));

                        sumaNeto += factura.getNetoGravado() + factura.getNetoNoGravado();
                        sumaIVA += factura.getIVA();
                        sumaTotal += factura.getTotal();
                        factura.setCodigo(-1);
                        listaAfip.add(factura);
//                        System.out.println(factura.toString());
                    }
                }
            }
            // Closing the workbook
            workbook.close();

            dataAfip.addAll(listaAfip);
            tblAfip.setItems(dataAfip);

            lblAfipInfo.setText("Cantidad Comprobantes:" + listaAfip.size() + " - Total Neto=" + String.format("$%,.2f", sumaNeto) + " - Total IVA=" + String.format("$%,.2f", sumaIVA) + " - TOTAL=" + String.format("$%,.2f", sumaTotal));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double setNumerosAfip(Cell cell, double signo) {
        double valor = 0.0;
        try {
            if (cell == null) {
                return 0.0;
            }
            String str = cell.toString();
            if (str.trim().isEmpty()) {
                return 0.0;
            }
            valor = Double.parseDouble(str) * signo;
        } catch (Exception e) {
            return 0.0;
        }
        return valor;
    }

    private void Boom() {
        fileSistema.setText("C:\\Java\\lista_campogi.xlsx");
        this.abrirListaSistema();

        fileAfip.setText("C:\\Java\\lista_afip.xlsx");
        this.abrirListaAfip();

        this.boomMix();
        //new ExcelWriter().guardarTodo(listaSistema, listaAfip);
        //new CompararFC(listaSistema, listaAfip);
    }

    private void boomMix() {
        try {
            ComparaFacturas();

            for (Factura factura : listaSistema) {
                listaResultado.add(factura);
            }
            for (Factura factura : listaAfip) {
                listaResultado.add(factura);
            }
            dataResultado.addAll(listaResultado);
            tblResultado.setItems(dataResultado);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void ComparaFacturas() {
        try {
            int fc = 1;
            for (int s = 0; s < listaSistema.size(); s++) {
                Factura fcSistema = listaSistema.get(s);
                for (int a = 0; a < listaAfip.size(); a++) {
                    Factura fcAfip = listaAfip.get(a);

                    if (fcSistema.equals(fcAfip)) {
                        listaSistema.get(s).setCodigo(fc);
                        listaAfip.get(a).setCodigo(fc);
                        System.out.println("ar.nex.factura.Comprar_facturasController.ComparaFacturas()" + listaSistema.get(s).getCodigo());
                        fc++;
                    }

                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean Iguales(Factura sis, Factura afip) {
        try {
            if (sis.getNumeroComprobante() == afip.getNumeroComprobante()) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    String openFile(ActionEvent event, int from) {
        String pathFile = "Error";
        try {
            FileChooser fileChooser = new FileChooser();
            // Set extension filter
            List<String> listExt = new ArrayList<String>();
            listExt.add("*.xls");
            listExt.add("*.xlsx");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                    "Files  (*.xls)", listExt);
            fileChooser.getExtensionFilters().add(extFilter);

            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Show save file dialog        
            File file = fileChooser.showOpenDialog(app_stage);

            if (file != null) {
                if (from != 1) {
                    fileSistema.setText(file.getAbsolutePath());
                    abrirListaSistema();
                } else {
                    fileAfip.setText(file.getAbsolutePath());
                    abrirListaAfip();
                }
                //pathFile = file.getAbsolutePath();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pathFile;
    }

    private void guardarResultado() {
        try {
            new ExcelManage().guardarResultado(listaResultado);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void Search() {
        searchBox.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super Factura>) fc -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (fc.getDenominacion().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (fc.getCuit().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Factura> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblResultado.comparatorProperty());
        tblResultado.setItems(sortedData);
    }
}
