import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.lang.String;
import javax.swing.*;

public class Interaktsioon1 extends Applet implements ActionListener{
static double raha = 1000;
int nr = 0;
int aktsiakr;
int aktsiasn;
TextField om = new TextField("0"); 
Label hind = new Label(String.valueOf(aktsiakr+"krooni ja"+aktsiasn+"senti alles")); 
Label arv = new Label(String.valueOf(nr)); 
static Label mony = new Label(String.valueOf((int)raha+" krooni "+(int)(100*(raha-Math.floor(raha)))+" senti"));
Button uus = new Button("Uuenda");
int keskhindkr; 
int keskhindsn;
int madalhindkr;
int madalhindsn;
int korghindkr;
String borsifail;
int kord=1;
int korghindsn;
static int feb = 31;
static int mar = 59;
static int apr = 90;
static int mai = 120;
static int jun = 151;
static int jul = 181;
static int aug = 212;
static int sep = 243;
static int okt = 273;
static int nov = 304;
static int dec = 334;
static int kuud[] = new int[12];
static int kordaja = 1;
static String paev = "3";
static String kuu = "01";
static boolean al = false;
static String aasta = "2000";
String p2ev = "3";
String kyy = "01";
String a2sta = "2000";
static Label kuup2ev = new Label(paev+"."+kuu+"."+aasta);
BufferedReader aktsiis; 
javax.swing.Timer n = new javax.swing.Timer(500,this);


	public Interaktsioon1(String bursifail, String borsifailinimi, int kr, int sn) throws IOException{
add(hind);
add(uus);
add(om);add(arv);
om.addActionListener(this);
uus.addActionListener(this);
borsifail=bursifail;
aktsiis = new BufferedReader(new FileReader(borsifail));
aktsiakr=kr; aktsiasn=sn;
hind.setText(String.valueOf(aktsiakr+" kr "+aktsiasn+" sn"));
Label nimi = new Label(borsifailinimi);
add(nimi);
n.start();
}



	public int p2evalugeja(int kuukene,int aastakene){
int x = (aastakene-2000)*365;
if(((aastakene % 4)==0)){
x=x+1+(aastakene-2000)/4;
}
for(int i=1; i<kuukene; i++){
x=x+kuud[i];
}
return x;
}

	public void Borsilugeja() throws IOException{
String rida = aktsiis.readLine();
StringTokenizer ridakene = new StringTokenizer(rida,"\t",false);
String kuupaev = ridakene.nextToken();
String keskhind = ridakene.nextToken();
String korghind = ridakene.nextToken();
String madalhind = ridakene.nextToken();


	StringTokenizer aeg = new StringTokenizer(kuupaev,".",false);
p2ev = aeg.nextToken();
kyy = aeg.nextToken();
a2sta = aeg.nextToken();
if(((Integer.parseInt(paev)+1)>Integer.parseInt(p2ev))||( Integer.parseInt(kuu)>Integer.parseInt(kyy) )||(Integer.parseInt(aasta)>Integer.parseInt(a2sta) ) ){for(int w=0; w<(Integer.parseInt(paev) + p2evalugeja(Integer.parseInt(kuu),Integer.parseInt(aasta))- Integer.parseInt(p2ev) - p2evalugeja(Integer.parseInt(kyy),Integer.parseInt(a2sta))); w++){

aktsiis.readLine(); Borsilugeja();}} else {
	try {
StringTokenizer keskkroonidsendid = new StringTokenizer(keskhind, ",", false);
keskhindkr = Integer.parseInt(keskkroonidsendid.nextToken());
keskhindsn = Integer.parseInt(keskkroonidsendid.nextToken());
if(keskhindsn<10){keskhindsn=10*keskhindsn;}
} catch(NoSuchElementException kesktokenizerviga) {
keskhindkr = Integer.parseInt(keskhind);
keskhindsn = 0;
}
	try {
StringTokenizer madalkroonidsendid = new StringTokenizer(madalhind, ",", false);
madalhindkr = Integer.parseInt(madalkroonidsendid.nextToken());
madalhindsn = Integer.parseInt(madalkroonidsendid.nextToken());
if(madalhindsn<10){madalhindsn=10*madalhindsn;}
} catch(NoSuchElementException madaltokenizerviga) {
madalhindkr = Integer.parseInt(madalhind);
madalhindsn = 0;
}
	try {
StringTokenizer korgkroonidsendid = new StringTokenizer(korghind, ",", false);
korghindkr = Integer.parseInt(korgkroonidsendid.nextToken());
korghindsn = Integer.parseInt(korgkroonidsendid.nextToken());
if(korghindsn<10){korghindsn=10*korghindsn;}
} catch(NoSuchElementException korgtokenizerviga) {
korghindkr = Integer.parseInt(korghind);
korghindsn = 0;
}

}

}


public static Label rahanaitaja(){ 
return mony; 
}


public static Label kuupaevaraja(){
return kuup2ev;
}



	public void actionPerformed(ActionEvent a){
System.out.println(borsifail);
mony.setText(String.valueOf((int)raha+" krooni "+(int)(100*(raha-Math.floor(raha)))+" senti"));
/*
if( ((Integer.parseInt(paev)+1)>Integer.parseInt(p2ev))||( Integer.parseInt(kuu)>Integer.parseInt(kyy) )||(Integer.parseInt(aasta)>Integer.parseInt(a2sta) ) ){
*/
if((a.getSource()==uus)||(a.getSource()==n)){
kordaja++;
 if((kordaja>3)){try{
Borsilugeja();
paev=p2ev;
kuu=kyy;
aasta=a2sta;
kuup2ev.setText(paev+"."+kuu+"."+aasta);
kordaja=1;
kord--;
} catch(IOException Borsilugeja) {System.out.println("VIGA: Borsifailid on vigased voi puuduvad");
mony.setText("VIGA: Börsifailid on vigased või puuduvad");}
}

if(kordaja==1){
if((korghindkr+korghindsn)>0){
aktsiakr=korghindkr; aktsiasn = korghindsn;
}
} else {if(kordaja==2){
if((madalhindkr+madalhindsn)>0){
aktsiakr=madalhindkr; aktsiasn = madalhindsn;
}
} else {
if((keskhindkr+keskhindsn)>0){
aktsiakr=keskhindkr;; aktsiasn = keskhindsn;
}
}
}
hind.setText(String.valueOf(aktsiakr+" kr "+aktsiasn+" sn"));
} else {
if(a.getSource()==om){
if(((aktsiakr+aktsiasn/100.00)*Integer.parseInt(om.getText()))<raha){
raha = raha - (aktsiakr+aktsiasn/100.00)*Integer.parseInt(om.getText());
mony.setText(String.valueOf((int)raha+" krooni "+(int)(100*(raha-Math.floor(raha)))+" senti"));
nr = nr+Integer.parseInt(om.getText());
arv.setText(String.valueOf(nr));
}
}
} 
/*} else {System.out.println("liiga vara");} */
}
public void Meny(){
}
public static void Seakuudpaika(int[] quud){ 
quud[1] = feb;
quud[2] = mar;
quud[3] = apr;
quud[4] = mai;
quud[5] = jun;
quud[6] = jul;
quud[7] = aug;
quud[8] = sep;
quud[9] = okt;
quud[10] = nov;
quud[11] = dec;}

	public static void main(String[] arg) throws IOException{
Seakuudpaika(kuud);
Frame f=new Frame("Börsike");
f.setSize(850,500);
f.setLayout(new GridLayout(1,3));
f.add(Interaktsioon1.rahanaitaja());
f.add(Interaktsioon1.kuupaevaraja());
f.add(new Interaktsioon1("MKO1Tmod.txt","Merko ehitus",18,20));
f.add(new Interaktsioon1("NRM1Tmod.txt","       Norma      ",41,0));
f.add(new Interaktsioon1("SPO1Tmod.txt","Sampo Pank",10,53));
f.add(new Interaktsioon1("TKM1Tmod.txt","Tallinna Kaubamaja", 118, 18));
f.add(new Interaktsioon1("EYPmod.txt","Eesti Ühispank", 27, 67));
f.setVisible(true);
f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

}
}
