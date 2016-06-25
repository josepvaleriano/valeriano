package test.valeriano.mx.acalculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*Metodo principal de la calculadora*/
public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private Boolean typeCalculator = true;
    private String  typeOperation= "";
    private Boolean evaluateOperation = false;
    private EditText number1;
    private EditText operator;
    private EditText number2;
    private int addPoint = 0;

    private Button btnDecBin;
    private Button btnMod;
    private Button btnClear;
    private Button btnDiv;
    private Button btnMul;
    private Button btnRes;
    private Button btnSum;
    private Button btnPoint;
    private Button btnEquals;
    private Button btnNum0;
    private Button btnNum1;
    private Button btnNum2;
    private Button btnNum3;
    private Button btnNum4;
    private Button btnNum5;
    private Button btnNum6;
    private Button btnNum7;
    private Button btnNum8;
    private Button btnNum9;

    /*Método de creación */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        listenerdButton();

    }

    /*Método para canalizar los eventos*/

    @Override
    public void onClick(View view) {
        String n1 = number1.getText().toString();
        String n2 = number2.getText().toString();
        boolean executeEquals = false;
        try {
            switch (view.getId()) {
                case R.id.btnDecBin:
                    typeCalculator = !typeCalculator;
                    number1.setText("");
                    number2.setText("");
                    operator.setText("");
                    btnNum2.setEnabled(typeCalculator);
                    btnNum3.setEnabled(typeCalculator);
                    btnNum4.setEnabled(typeCalculator);
                    btnNum5.setEnabled(typeCalculator);
                    btnNum6.setEnabled(typeCalculator);
                    btnNum7.setEnabled(typeCalculator);
                    btnNum8.setEnabled(typeCalculator);
                    btnNum9.setEnabled(typeCalculator);
                    btnPoint.setEnabled(typeCalculator);
                    btnMod.setEnabled(typeCalculator);
                    evaluateOperation = false;
                    addPoint = 0;
                    break;
                case R.id.btnClear:
                    number1.setText("");
                    operator.setText("");
                    number2.setText("");
                    evaluateOperation = false;
                    addPoint = 0;
                    break;
                case R.id.btnEquals:
                    executeEquals = true;
                    evaluateOperation(executeEquals, n1, n2);
                    evaluateOperation = true;
                    break;
                case R.id.btnDiv:
                    if (n1.trim().length() == 0 && n2.trim().length() != 0 && !n2.equals(".")) {
                        number1.setText("" + n2);
                        number2.setText("");
                        typeOperation = "/";
                        operator.setText(typeOperation);
                        addPoint = 0;
                        evaluateOperation = false;
                    } else if (n1.trim().length() != 0 && n2.trim().length() != 0 && !n2.equals(".")) {
                        executeEquals = false;
                        evaluateOperation(executeEquals, n1, n2);
                        typeOperation = "/";
                        operator.setText(typeOperation);
                        addPoint = 0;
                    } else if (n1.trim().length() != 0 && n2.trim().length() == 0) {
                        typeOperation = "/";
                        operator.setText(typeOperation);
                        addPoint = 0;
                    }
                    break;
                case R.id.btnMul:
                    if (n1.trim().length() == 0 && n2.trim().length() != 0 && !n2.equals(".")) {
                        number1.setText("" + n2);
                        number2.setText("");
                        typeOperation = "x";
                        operator.setText(typeOperation);
                        addPoint = 0;
                        evaluateOperation = false;
                    } else if (n1.trim().length() != 0 && n2.trim().length() != 0 && !n2.equals(".")) {
                        executeEquals = false;
                        evaluateOperation(executeEquals, n1, n2);
                        typeOperation = "x";
                        operator.setText(typeOperation);
                        addPoint = 0;
                    } else if (n1.trim().length() != 0 && n2.trim().length() == 0) {
                        typeOperation = "x";
                        operator.setText(typeOperation);
                        addPoint = 0;
                    }
                    break;
                case R.id.btnRes:
                    if (n1.trim().length() == 0 && n2.trim().length() != 0 && !n2.equals(".")) {
                        number1.setText("" + n2);
                        number2.setText("");
                        typeOperation = "-";
                        operator.setText(typeOperation);
                        addPoint = 0;
                        evaluateOperation = false;
                    } else if (n1.trim().length() != 0 && n2.trim().length() != 0 && !n2.equals(".")) {
                        executeEquals = false;
                        evaluateOperation(executeEquals, n1, n2);
                        typeOperation = "-";
                        operator.setText(typeOperation);
                        addPoint = 0;
                    } else if (n1.trim().length() != 0 && n2.trim().length() == 0) {
                        typeOperation = "-";
                        operator.setText(typeOperation);
                        addPoint = 0;
                    }
                    break;
                case R.id.btnSum:
                    if (n1.trim().length() == 0 && n2.trim().length() != 0 && !n2.equals(".")) {
                        number1.setText("" + n2);
                        number2.setText("");
                        typeOperation = "+";
                        operator.setText(typeOperation);
                        addPoint = 0;
                        evaluateOperation = false;
                    } else if (n1.trim().length() != 0 && n2.trim().length() != 0 && !n2.equals(".")) {
                        executeEquals = false;
                        evaluateOperation(executeEquals, n1, n2);
                        typeOperation = "+";
                        operator.setText(typeOperation);
                        addPoint = 0;
                    } else if (n1.trim().length() != 0 && n2.trim().length() == 0) {
                        typeOperation = "+";
                        operator.setText(typeOperation);
                        addPoint = 0;
                    }
                    break;
                case R.id.btnMod:
                    if (n1.trim().length() == 0 && n2.trim().length() != 0 && !n2.equals(".")) {
                        number1.setText("" + n2);
                        number2.setText("");
                        typeOperation = "%";
                        operator.setText(typeOperation);
                        addPoint = 0;
                        evaluateOperation = false;
                    } else if (n1.trim().length() != 0 && n2.trim().length() != 0 && !n2.equals(".")) {
                        executeEquals = false;
                        evaluateOperation(executeEquals, n1, n2);
                        typeOperation = "%";
                        operator.setText(typeOperation);
                        addPoint = 0;
                    } else if (n1.trim().length() != 0 && n2.trim().length() == 0) {
                        typeOperation = "%";
                        operator.setText(typeOperation);
                        addPoint = 0;
                    }

                    break;

                case R.id.btnPoint:
                    if (evaluateOperation) {
                        n2 = "";
                        evaluateOperation = !evaluateOperation;
                    }
                    if (addPoint == 0) {
                        number2.setText(n2 + ".");
                        addPoint++;
                    }
                    break;
                case R.id.btn9:
                    if (evaluateOperation) {
                        n2 = "";
                        evaluateOperation = !evaluateOperation;
                    }
                    number2.setText(n2 + "9");
                    break;
                case R.id.btn8:
                    if (evaluateOperation) {
                        n2 = "";
                        evaluateOperation = !evaluateOperation;
                    }
                    number2.setText(n2 + "8");
                    break;
                case R.id.btn7:
                    if (evaluateOperation) {
                        n2 = "";
                        evaluateOperation = !evaluateOperation;
                    }
                    number2.setText(n2 + "7");
                    break;
                case R.id.btn6:
                    if (evaluateOperation) {
                        n2 = "";
                        evaluateOperation = !evaluateOperation;
                    }
                    number2.setText(n2 + "6");
                    break;
                case R.id.btn5:
                    if (evaluateOperation) {
                        n2 = "";
                        evaluateOperation = !evaluateOperation;
                    }
                    number2.setText(n2 + "5");
                    break;
                case R.id.btn4:
                    if (evaluateOperation) {
                        n2 = "";
                        evaluateOperation = !evaluateOperation;
                    }
                    number2.setText(n2 + "4");
                    break;
                case R.id.btn3:
                    if (evaluateOperation) {
                        n2 = "";
                        evaluateOperation = !evaluateOperation;
                    }
                    number2.setText(n2 + "3");
                    break;
                case R.id.btn2:
                    if (evaluateOperation) {
                        n2 = "";
                        evaluateOperation = !evaluateOperation;
                    }
                    number2.setText(n2 + "2");
                    break;
                case R.id.btn1:
                    if (evaluateOperation) {
                        n2 = "";
                        evaluateOperation = !evaluateOperation;
                    }
                    number2.setText(n2 + "1");
                    break;
                case R.id.btn0:
                    if (evaluateOperation) {
                        n2 = "";
                        evaluateOperation = !evaluateOperation;
                    }
                    number2.setText(n2 + "0");
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),R.string.error_operation,Toast.LENGTH_SHORT).show();
        }

    }


    /*Método privado encargado de realizar las operaciones @param executeEquals cuando se presiona el igual, también incluye las operaciones binarias
    * @String n1 valor de la celda 1
    * @String n2 valor de la celda 2
    * */
    private void evaluateOperation(boolean executeEquals, String n1, String n2) {
        String operacion ="" ;
        switch (typeOperation){
            case "/":
                float isZero = Float.parseFloat(n2);
                if (!TextUtils.isEmpty(n2) && !TextUtils.isEmpty(n1) ){
                    if(!typeCalculator && isZero!=0 ) {
                        int r = Integer.parseInt(n1, 2) / Integer.parseInt(n2, 2);
                        operacion = Integer.toString(r,2);
                    }
                    else if(typeCalculator && isZero!=0 ) {
                        operacion = (Float.parseFloat(n1) / Float.parseFloat(n2))+"" ;
                    }
                    else
                        Toast.makeText(getApplicationContext(),R.string.error_operation,Toast.LENGTH_SHORT).show();
                    if (executeEquals) {
                        number2.setText("" + operacion);
                        number1.setText("");
                        operator.setText("");
                    }
                    else{
                        number1.setText("" + operacion);
                        number2.setText("");
                    }
                }
                break;
            case "x":
                if (!TextUtils.isEmpty(n2) && !TextUtils.isEmpty(n1) ){
                    if(!typeCalculator) {
                        int r = Integer.parseInt(n1, 2) * Integer.parseInt(n2, 2);
                        operacion = Integer.toString(r,2);
                    }
                    else {
                        operacion = (Float.parseFloat(n1) * Float.parseFloat(n2))+"" ;
                    }
                    if (executeEquals) {
                        number2.setText("" + operacion);
                        number1.setText("");
                        operator.setText("");
                    }
                    else{
                        number1.setText("" + operacion);
                        number2.setText("");
                    }
                }
                break;
            case "+":
                if (!TextUtils.isEmpty(n2) && !TextUtils.isEmpty(n1) ){
                    if(!typeCalculator) {
                        int r = Integer.parseInt(n1, 2) + Integer.parseInt(n2, 2);
                        operacion = Integer.toString(r,2);
                    }
                    else {
                        operacion = (Float.parseFloat(n1) + Float.parseFloat(n2))+"" ;
                    }
                    if (executeEquals) {
                        number2.setText("" + operacion);
                        number1.setText("");
                        operator.setText("");
                    }
                    else{
                        number1.setText("" + operacion);
                        number2.setText("");
                    }
                }
                break;
            case "-":
                if (!TextUtils.isEmpty(n2) && !TextUtils.isEmpty(n1) ){
                    if(!typeCalculator) {
                        int r = Integer.parseInt(n1, 2) - Integer.parseInt(n2, 2);
                        operacion = Integer.toString(r,2);
                    }
                    else {
                        operacion = (Float.parseFloat(n1) - Float.parseFloat(n2))+"" ;
                    }
                    if (executeEquals) {
                        number2.setText("" + operacion);
                        number1.setText("");
                        operator.setText("");
                    }
                    else{
                        number1.setText("" + operacion);
                        number2.setText("");
                    }
                }
                break;
            case "%":
                isZero = Float.parseFloat(n2);
                if (!TextUtils.isEmpty(n2) && !TextUtils.isEmpty(n1) ){
                    if(!typeCalculator && isZero!=0 ) {
                        int r = Integer.parseInt(n1, 2) % Integer.parseInt(n2, 2);
                        operacion = Integer.toString(r,2);
                    }
                    else if(typeCalculator && isZero!=0 ) {
                        operacion = (Float.parseFloat(n1) % Float.parseFloat(n2))+"" ;
                    }
                    else
                        Toast.makeText(getApplicationContext(),R.string.error_operation,Toast.LENGTH_SHORT).show();
                    if (executeEquals) {
                        number2.setText("" + operacion);
                        number1.setText("");
                        operator.setText("");
                    }
                    else{
                        number1.setText("" + operacion);
                        number2.setText("");
                    }
                }
                break;
        }

    }

    /*Metodo para buscar los elementos en la vista*/
    private void findView(){
        number1 = (EditText) findViewById(R.id.txtNumber1);
        operator = (EditText) findViewById(R.id.txtOperator);
        number2 = (EditText) findViewById(R.id.txtNumber2);
        btnDecBin = (Button) findViewById(R.id.btnDecBin);
        btnClear  = (Button) findViewById(R.id.btnClear);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnRes = (Button) findViewById(R.id.btnRes);
        btnSum = (Button) findViewById(R.id.btnSum);
        btnMod = (Button) findViewById(R.id.btnMod);
        btnPoint = (Button) findViewById(R.id.btnPoint);
        btnEquals = (Button) findViewById(R.id.btnEquals);
        btnNum0  = (Button) findViewById(R.id.btn0);
        btnNum1 = (Button) findViewById(R.id.btn1);
        btnNum2 = (Button) findViewById(R.id.btn2);
        btnNum3 = (Button) findViewById(R.id.btn3);
        btnNum4 = (Button) findViewById(R.id.btn4);
        btnNum5 = (Button) findViewById(R.id.btn5);
        btnNum6 = (Button) findViewById(R.id.btn6);
        btnNum7 = (Button) findViewById(R.id.btn7);
        btnNum8 = (Button) findViewById(R.id.btn8);
        btnNum9 = (Button) findViewById(R.id.btn9);

    }

    /*Metodo para habilitar los botones*/
    private void listenerdButton() {
        findViewById(R.id.btnDecBin).setOnClickListener(this);
        findViewById(R.id.btnClear).setOnClickListener(this);
        findViewById(R.id.btnDiv).setOnClickListener(this);
        findViewById(R.id.btnMul).setOnClickListener(this);
        findViewById(R.id.btnRes).setOnClickListener(this);
        findViewById(R.id.btnSum).setOnClickListener(this);
        findViewById(R.id.btnPoint).setOnClickListener(this);
        findViewById(R.id.btnEquals).setOnClickListener(this);
        findViewById(R.id.btnMod).setOnClickListener(this);
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
    }
}
