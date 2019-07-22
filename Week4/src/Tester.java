public class Tester {
public static void main(String args[]) {

   // CaesarCipher cc = new CaesarCipher(5);
   /* System.out.println(cc.encrypt("Coal-black is better than another hue,\n" +
            "In that it scorns to bear another hue;\n" +
            "For all the water in the ocean\n" +
            "Can never turn the swan's black legs to white,\n" +
            "Although she lave them hourly in the flood."));
    System.out.println(cc.decrypt("\n" +
            "Gsep-fpego mw fixxiv xler ersxliv lyi,\n" +
            "Mr xlex mx wgsvrw xs fiev ersxliv lyi;\n" +
            "Jsv epp xli aexiv mr xli sgier\n" +
            "Ger riziv xyvr xli waer'w fpego pikw xs almxi,\n" +
            "Epxlsykl wli pezi xliq lsyvpc mr xli jpssh.")); */
  /*  CaesarCracker c=new CaesarCracker();
   System.out.println (c.decrypt("\n" +
           "Gsep-fpego mw fixxiv xler ersxliv lyi,\n" +
           "Mr xlex mx wgsvrw xs fiev ersxliv lyi;\n" +
           "Jsv epp xli aexiv mr xli sgier\n" +
           "Ger riziv xyvr xli waer'w fpego pikw xs almxi,\n" +
           "Epxlsykl wli pezi xliq lsyvpc mr xli jpssh."));
    CaesarCracker c1=new CaesarCracker('a');
    System.out.println (c1.decrypt("\n" +
            "Gsep-fpego mw fixxiv xler ersxliv lyi,\n" +
            "Mr xlex mx wgsvrw xs fiev ersxliv lyi;\n" +
            "Jsv epp xli aexiv mr xli sgier\n" +
            "Ger riziv xyvr xli waer'w fpego pikw xs almxi,\n" +
            "Epxlsykl wli pezi xliq lsyvpc mr xli jpssh."));*/
/*int[] arr={17,14,12,4};
VigenereCipher vc=new VigenereCipher(arr);
    System.out.println(vc.encrypt("Coal-black is better than another hue,\n" +
            "In that it scorns to bear another hue;\n" +
            "For all the water in the ocean\n" +
            "Can never turn the swan's black legs to white,\n" +
            "Although she lave them hourly in the flood."));
 System.out.println(vc.decrypt("Tcmp-pxety mj nikhqv htee mrfhtii tyv,\n" +
         "Me flrh mk egffzw ha ssmv ozskvqv vgi;\n" +
         "Rsi mpc flv ieksd zb xys stsmr\n" +
         "Qmr bqzvf xlfz kvq jkmr'g fcooo zqkj fs ktmks,\n" +
         "Rzflfisl gti zmzv flva lfidpp ur hti txsfr."));*/
//VigenereBreaker vb=new VigenereBreaker();
//vb.testTryKeyLength();
//vb.testSliceString();
//vb.breakVigenere();

   /* VigenereBreakerUnknownKey vbuk=new VigenereBreakerUnknownKey();
    vbuk.breakVigenere();*/
VigenereBreakerUnknownLangAndKey vbulk=new VigenereBreakerUnknownLangAndKey();
vbulk.breakVigenere();

}
}
