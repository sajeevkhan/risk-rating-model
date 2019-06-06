package com.pfs.riskmodel.businessconfig;

import com.pfs.riskmodel.domain.*;
import com.pfs.riskmodel.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Slf4j
@Component
@RequiredArgsConstructor



public class CreditRatingsScoresInitializer implements CommandLineRunner{

    @Autowired
    private CreditRatingMapRepository creditRatingMapRepository;

    @Autowired
    private CreditRatingSourceRepository creditRatingSourceRepository;


    @Override
    public void run(String... strings) throws Exception {

          if(creditRatingSourceRepository.count() == 0) {
                CreditRatingSource c1 = new CreditRatingSource(null, "CRISIL", "CRISIL");
                CreditRatingSource c2 = new CreditRatingSource(null, "ICRA", "ICRA");
                CreditRatingSource c3 = new CreditRatingSource(null, "CARE", "CARE");
                CreditRatingSource c4 = new CreditRatingSource(null, "BWR", "BWR");
                CreditRatingSource c9 = new CreditRatingSource(null, "SMERA", "SMERA");
                CreditRatingSource c5 = new CreditRatingSource(null, "S&P", "S&P");
                CreditRatingSource c6 = new CreditRatingSource(null, "Moodys", "Moodys");
                CreditRatingSource c7 = new CreditRatingSource(null, "Fitch", "Fitch");
                CreditRatingSource c8 = new CreditRatingSource(null, "PFS", "PFS");

                creditRatingSourceRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9));

                log.info("-------------------------- Added Credit Rating Data for : " + c1.getCode());

          }


      if(creditRatingMapRepository.count() == 0) {
            CreditRatingSource c1 = creditRatingSourceRepository.findByCode("CRISIL");
            CreditRatingMap m1=new CreditRatingMap(null,c1,'1',"AAA",1);

            creditRatingMapRepository.save(m1);

            //CreditRatingSource c2 = creditRatingSourceRepository.findByCode("CRISIL");
            CreditRatingMap m2=new CreditRatingMap(null,c1,'1',"AA+",1);
            creditRatingMapRepository.save(m2);

            CreditRatingMap m3=new CreditRatingMap(null,c1,'1',"AA",2);creditRatingMapRepository.saveAll(Arrays.asList(m3));
            CreditRatingMap m4=new CreditRatingMap(null,c1,'1',"AA-",2);creditRatingMapRepository.saveAll(Arrays.asList(m4));
            CreditRatingMap m5=new CreditRatingMap(null,c1,'1',"A+",3);creditRatingMapRepository.saveAll(Arrays.asList(m5));
            CreditRatingMap m6=new CreditRatingMap(null,c1,'1',"A",3);creditRatingMapRepository.saveAll(Arrays.asList(m6));
            CreditRatingMap m7=new CreditRatingMap(null,c1,'1',"A-",4);creditRatingMapRepository.saveAll(Arrays.asList(m7));
            CreditRatingMap m8=new CreditRatingMap(null,c1,'1',"BBB+",4);creditRatingMapRepository.saveAll(Arrays.asList(m8));
            CreditRatingMap m9=new CreditRatingMap(null,c1,'1',"BBB",5);creditRatingMapRepository.saveAll(Arrays.asList(m9));
            CreditRatingMap m10=new CreditRatingMap(null,c1,'1',"BBB-",5);creditRatingMapRepository.saveAll(Arrays.asList(m10));
            CreditRatingMap m11=new CreditRatingMap(null,c1,'1',"BB+",6);creditRatingMapRepository.saveAll(Arrays.asList(m11));
            CreditRatingMap m12=new CreditRatingMap(null,c1,'1',"BB",6);creditRatingMapRepository.saveAll(Arrays.asList(m12));
            CreditRatingMap m13=new CreditRatingMap(null,c1,'1',"BB-",7);creditRatingMapRepository.saveAll(Arrays.asList(m13));
            CreditRatingMap m14=new CreditRatingMap(null,c1,'1',"B+",7);creditRatingMapRepository.saveAll(Arrays.asList(m14));
            CreditRatingMap m15=new CreditRatingMap(null,c1,'1',"B",8);creditRatingMapRepository.saveAll(Arrays.asList(m15));
            CreditRatingMap m16=new CreditRatingMap(null,c1,'1',"B-",8);creditRatingMapRepository.saveAll(Arrays.asList(m16));
            CreditRatingMap m17=new CreditRatingMap(null,c1,'1',"C+",8);creditRatingMapRepository.saveAll(Arrays.asList(m17));
            CreditRatingMap m18=new CreditRatingMap(null,c1,'1',"C",9);creditRatingMapRepository.saveAll(Arrays.asList(m18));
            CreditRatingMap m19=new CreditRatingMap(null,c1,'1',"C-",9);creditRatingMapRepository.saveAll(Arrays.asList(m19));
            CreditRatingMap m20=new CreditRatingMap(null,c1,'1',"D",10);creditRatingMapRepository.saveAll(Arrays.asList(m20));
            CreditRatingMap m21=new CreditRatingMap(null,c1,'0',"A1+",2);creditRatingMapRepository.saveAll(Arrays.asList(m21));
            CreditRatingMap m22=new CreditRatingMap(null,c1,'0',"A1",3);creditRatingMapRepository.saveAll(Arrays.asList(m22));
            CreditRatingMap m23=new CreditRatingMap(null,c1,'0',"A2+",4);creditRatingMapRepository.saveAll(Arrays.asList(m23));
            CreditRatingMap m24=new CreditRatingMap(null,c1,'0',"A2",5);creditRatingMapRepository.saveAll(Arrays.asList(m24));
            CreditRatingMap m25=new CreditRatingMap(null,c1,'0',"A3+",6);creditRatingMapRepository.saveAll(Arrays.asList(m25));
            CreditRatingMap m26=new CreditRatingMap(null,c1,'0',"A3",7);creditRatingMapRepository.saveAll(Arrays.asList(m26));
            CreditRatingMap m27=new CreditRatingMap(null,c1,'0',"A4+",8);creditRatingMapRepository.saveAll(Arrays.asList(m27));
            CreditRatingMap m28=new CreditRatingMap(null,c1,'0',"A4",9);creditRatingMapRepository.saveAll(Arrays.asList(m28));
            CreditRatingMap m29=new CreditRatingMap(null,c1,'0',"D",10);creditRatingMapRepository.saveAll(Arrays.asList(m29));

            c1 =creditRatingSourceRepository.findByCode("ICRA");
            CreditRatingMap m31=new CreditRatingMap(null,c1,'1',"AAA",1);creditRatingMapRepository.saveAll(Arrays.asList(m31));
            CreditRatingMap m32=new CreditRatingMap(null,c1,'1',"AA+",1);creditRatingMapRepository.saveAll(Arrays.asList(m32));
            CreditRatingMap m33=new CreditRatingMap(null,c1,'1',"AA",2);creditRatingMapRepository.saveAll(Arrays.asList(m33));
            CreditRatingMap m34=new CreditRatingMap(null,c1,'1',"AA-",2);creditRatingMapRepository.saveAll(Arrays.asList(m34));
            CreditRatingMap m35=new CreditRatingMap(null,c1,'1',"A+",3);creditRatingMapRepository.saveAll(Arrays.asList(m35));
            CreditRatingMap m36=new CreditRatingMap(null,c1,'1',"A",3);creditRatingMapRepository.saveAll(Arrays.asList(m36));
            CreditRatingMap m37=new CreditRatingMap(null,c1,'1',"A-",4);creditRatingMapRepository.saveAll(Arrays.asList(m37));
            CreditRatingMap m38=new CreditRatingMap(null,c1,'1',"BBB+",4);creditRatingMapRepository.saveAll(Arrays.asList(m38));
            CreditRatingMap m39=new CreditRatingMap(null,c1,'1',"BBB",5);creditRatingMapRepository.saveAll(Arrays.asList(m39));
            CreditRatingMap m40=new CreditRatingMap(null,c1,'1',"BBB-",5);creditRatingMapRepository.saveAll(Arrays.asList(m40));
            CreditRatingMap m41=new CreditRatingMap(null,c1,'1',"BB+",6);creditRatingMapRepository.saveAll(Arrays.asList(m41));
            CreditRatingMap m42=new CreditRatingMap(null,c1,'1',"BB",6);creditRatingMapRepository.saveAll(Arrays.asList(m42));
            CreditRatingMap m43=new CreditRatingMap(null,c1,'1',"BB-",7);creditRatingMapRepository.saveAll(Arrays.asList(m43));
            CreditRatingMap m44=new CreditRatingMap(null,c1,'1',"B+",7);creditRatingMapRepository.saveAll(Arrays.asList(m44));
            CreditRatingMap m45=new CreditRatingMap(null,c1,'1',"B",8);creditRatingMapRepository.saveAll(Arrays.asList(m45));
            CreditRatingMap m46=new CreditRatingMap(null,c1,'1',"B-",8);creditRatingMapRepository.saveAll(Arrays.asList(m46));
            CreditRatingMap m47=new CreditRatingMap(null,c1,'1',"C+",8);creditRatingMapRepository.saveAll(Arrays.asList(m47));
            CreditRatingMap m48=new CreditRatingMap(null,c1,'1',"C",9);creditRatingMapRepository.saveAll(Arrays.asList(m48));
            CreditRatingMap m49=new CreditRatingMap(null,c1,'1',"C-",9);creditRatingMapRepository.saveAll(Arrays.asList(m49));
            CreditRatingMap m50=new CreditRatingMap(null,c1,'1',"D",10);creditRatingMapRepository.saveAll(Arrays.asList(m50));
            CreditRatingMap m51=new CreditRatingMap(null,c1,'0',"A1+",2);creditRatingMapRepository.saveAll(Arrays.asList(m51));
            CreditRatingMap m52=new CreditRatingMap(null,c1,'0',"A1",3);creditRatingMapRepository.saveAll(Arrays.asList(m52));
            CreditRatingMap m53=new CreditRatingMap(null,c1,'0',"A2+",4);creditRatingMapRepository.saveAll(Arrays.asList(m53));
            CreditRatingMap m54=new CreditRatingMap(null,c1,'0',"A2",5);creditRatingMapRepository.saveAll(Arrays.asList(m54));
            CreditRatingMap m55=new CreditRatingMap(null,c1,'0',"A3+",6);creditRatingMapRepository.saveAll(Arrays.asList(m55));
            CreditRatingMap m56=new CreditRatingMap(null,c1,'0',"A3",7);creditRatingMapRepository.saveAll(Arrays.asList(m56));
            CreditRatingMap m57=new CreditRatingMap(null,c1,'0',"A4+",8);creditRatingMapRepository.saveAll(Arrays.asList(m57));
            CreditRatingMap m58=new CreditRatingMap(null,c1,'0',"A4",9);creditRatingMapRepository.saveAll(Arrays.asList(m58));
            CreditRatingMap m59=new CreditRatingMap(null,c1,'0',"D",10);creditRatingMapRepository.saveAll(Arrays.asList(m59));

            c1 = creditRatingSourceRepository.findByCode("CARE");

            CreditRatingMap m61=new CreditRatingMap(null,c1,'1',"AAA",1);creditRatingMapRepository.saveAll(Arrays.asList(m61));
            CreditRatingMap m62=new CreditRatingMap(null,c1,'1',"AA+",1);creditRatingMapRepository.saveAll(Arrays.asList(m62));
            CreditRatingMap m63=new CreditRatingMap(null,c1,'1',"AA",2);creditRatingMapRepository.saveAll(Arrays.asList(m63));
            CreditRatingMap m64=new CreditRatingMap(null,c1,'1',"AA-",2);creditRatingMapRepository.saveAll(Arrays.asList(m64));
            CreditRatingMap m65=new CreditRatingMap(null,c1,'1',"A+",3);creditRatingMapRepository.saveAll(Arrays.asList(m65));
            CreditRatingMap m66=new CreditRatingMap(null,c1,'1',"A",3);creditRatingMapRepository.saveAll(Arrays.asList(m66));
            CreditRatingMap m67=new CreditRatingMap(null,c1,'1',"A-",4);creditRatingMapRepository.saveAll(Arrays.asList(m67));
            CreditRatingMap m68=new CreditRatingMap(null,c1,'1',"BBB+",4);creditRatingMapRepository.saveAll(Arrays.asList(m68));
            CreditRatingMap m69=new CreditRatingMap(null,c1,'1',"BBB",5);creditRatingMapRepository.saveAll(Arrays.asList(m69));
            CreditRatingMap m70=new CreditRatingMap(null,c1,'1',"BBB-",5);creditRatingMapRepository.saveAll(Arrays.asList(m70));
            CreditRatingMap m71=new CreditRatingMap(null,c1,'1',"BB+",6);creditRatingMapRepository.saveAll(Arrays.asList(m71));
            CreditRatingMap m72=new CreditRatingMap(null,c1,'1',"BB",6);creditRatingMapRepository.saveAll(Arrays.asList(m72));
            CreditRatingMap m73=new CreditRatingMap(null,c1,'1',"BB-",7);creditRatingMapRepository.saveAll(Arrays.asList(m73));
            CreditRatingMap m74=new CreditRatingMap(null,c1,'1',"B+",7);creditRatingMapRepository.saveAll(Arrays.asList(m74));
            CreditRatingMap m75=new CreditRatingMap(null,c1,'1',"B",8);creditRatingMapRepository.saveAll(Arrays.asList(m75));
            CreditRatingMap m76=new CreditRatingMap(null,c1,'1',"B-",8);creditRatingMapRepository.saveAll(Arrays.asList(m76));
            CreditRatingMap m77=new CreditRatingMap(null,c1,'1',"C+",8);creditRatingMapRepository.saveAll(Arrays.asList(m77));
            CreditRatingMap m78=new CreditRatingMap(null,c1,'1',"C",9);creditRatingMapRepository.saveAll(Arrays.asList(m78));
            CreditRatingMap m79=new CreditRatingMap(null,c1,'1',"C-",9);creditRatingMapRepository.saveAll(Arrays.asList(m79));
            CreditRatingMap m80=new CreditRatingMap(null,c1,'1',"D",10);creditRatingMapRepository.saveAll(Arrays.asList(m80));
            CreditRatingMap m81=new CreditRatingMap(null,c1,'0',"A1+",2);creditRatingMapRepository.saveAll(Arrays.asList(m81));
            CreditRatingMap m82=new CreditRatingMap(null,c1,'0',"A1",3);creditRatingMapRepository.saveAll(Arrays.asList(m82));
            CreditRatingMap m83=new CreditRatingMap(null,c1,'0',"A2+",4);creditRatingMapRepository.saveAll(Arrays.asList(m83));
            CreditRatingMap m84=new CreditRatingMap(null,c1,'0',"A2",5);creditRatingMapRepository.saveAll(Arrays.asList(m84));
            CreditRatingMap m85=new CreditRatingMap(null,c1,'0',"A3+",6);creditRatingMapRepository.saveAll(Arrays.asList(m85));
            CreditRatingMap m86=new CreditRatingMap(null,c1,'0',"A3",7);creditRatingMapRepository.saveAll(Arrays.asList(m86));
            CreditRatingMap m87=new CreditRatingMap(null,c1,'0',"A4+",8);creditRatingMapRepository.saveAll(Arrays.asList(m87));
            CreditRatingMap m88=new CreditRatingMap(null,c1,'0',"A4",9);creditRatingMapRepository.saveAll(Arrays.asList(m88));
            CreditRatingMap m89=new CreditRatingMap(null,c1,'0',"D",10);creditRatingMapRepository.saveAll(Arrays.asList(m89));
            c1 = creditRatingSourceRepository.findByCode("BWR");
            CreditRatingMap m91=new CreditRatingMap(null,c1,'1',"AAA",1);creditRatingMapRepository.saveAll(Arrays.asList(m91));
            CreditRatingMap m92=new CreditRatingMap(null,c1,'1',"AA+",1);creditRatingMapRepository.saveAll(Arrays.asList(m92));
            CreditRatingMap m93=new CreditRatingMap(null,c1,'1',"AA",2);creditRatingMapRepository.saveAll(Arrays.asList(m93));
            CreditRatingMap m94=new CreditRatingMap(null,c1,'1',"AA-",2);creditRatingMapRepository.saveAll(Arrays.asList(m94));
            CreditRatingMap m95=new CreditRatingMap(null,c1,'1',"A+",3);creditRatingMapRepository.saveAll(Arrays.asList(m95));
            CreditRatingMap m96=new CreditRatingMap(null,c1,'1',"A",3);creditRatingMapRepository.saveAll(Arrays.asList(m96));
            CreditRatingMap m97=new CreditRatingMap(null,c1,'1',"A-",4);creditRatingMapRepository.saveAll(Arrays.asList(m97));
            CreditRatingMap m98=new CreditRatingMap(null,c1,'1',"BBB+",4);creditRatingMapRepository.saveAll(Arrays.asList(m98));
            CreditRatingMap m99=new CreditRatingMap(null,c1,'1',"BBB",5);creditRatingMapRepository.saveAll(Arrays.asList(m99));
            CreditRatingMap m100=new CreditRatingMap(null,c1,'1',"BBB-",5);creditRatingMapRepository.saveAll(Arrays.asList(m100));
            CreditRatingMap m101=new CreditRatingMap(null,c1,'1',"BB+",6);creditRatingMapRepository.saveAll(Arrays.asList(m101));
            CreditRatingMap m102=new CreditRatingMap(null,c1,'1',"BB",6);creditRatingMapRepository.saveAll(Arrays.asList(m102));
            CreditRatingMap m103=new CreditRatingMap(null,c1,'1',"BB-",7);creditRatingMapRepository.saveAll(Arrays.asList(m103));
            CreditRatingMap m104=new CreditRatingMap(null,c1,'1',"B+",7);creditRatingMapRepository.saveAll(Arrays.asList(m104));
            CreditRatingMap m105=new CreditRatingMap(null,c1,'1',"B",8);creditRatingMapRepository.saveAll(Arrays.asList(m105));
            CreditRatingMap m106=new CreditRatingMap(null,c1,'1',"B-",8);creditRatingMapRepository.saveAll(Arrays.asList(m106));
            CreditRatingMap m107=new CreditRatingMap(null,c1,'1',"C+",8);creditRatingMapRepository.saveAll(Arrays.asList(m107));
            CreditRatingMap m108=new CreditRatingMap(null,c1,'1',"C",9);creditRatingMapRepository.saveAll(Arrays.asList(m108));
            CreditRatingMap m109=new CreditRatingMap(null,c1,'1',"C-",9);creditRatingMapRepository.saveAll(Arrays.asList(m109));
            CreditRatingMap m110=new CreditRatingMap(null,c1,'1',"D",10);creditRatingMapRepository.saveAll(Arrays.asList(m110));
            CreditRatingMap m111=new CreditRatingMap(null,c1,'0',"A1+",2);creditRatingMapRepository.saveAll(Arrays.asList(m111));
            CreditRatingMap m112=new CreditRatingMap(null,c1,'0',"A1",3);creditRatingMapRepository.saveAll(Arrays.asList(m112));
            CreditRatingMap m113=new CreditRatingMap(null,c1,'0',"A2+",4);creditRatingMapRepository.saveAll(Arrays.asList(m113));
            CreditRatingMap m114=new CreditRatingMap(null,c1,'0',"A2",5);creditRatingMapRepository.saveAll(Arrays.asList(m114));
            CreditRatingMap m115=new CreditRatingMap(null,c1,'0',"A3+",6);creditRatingMapRepository.saveAll(Arrays.asList(m115));
            CreditRatingMap m116=new CreditRatingMap(null,c1,'0',"A3",7);creditRatingMapRepository.saveAll(Arrays.asList(m116));
            CreditRatingMap m117=new CreditRatingMap(null,c1,'0',"A4+",8);creditRatingMapRepository.saveAll(Arrays.asList(m117));
            CreditRatingMap m118=new CreditRatingMap(null,c1,'0',"A4",9);creditRatingMapRepository.saveAll(Arrays.asList(m118));
            CreditRatingMap m119=new CreditRatingMap(null,c1,'0',"D",10);creditRatingMapRepository.saveAll(Arrays.asList(m119));


            c1 = creditRatingSourceRepository.findByCode("SMERA");
            CreditRatingMap m121=new CreditRatingMap(null,c1,'1',"AAA",1);creditRatingMapRepository.saveAll(Arrays.asList(m121));
            CreditRatingMap m122=new CreditRatingMap(null,c1,'1',"AA+",1);creditRatingMapRepository.saveAll(Arrays.asList(m122));
            CreditRatingMap m123=new CreditRatingMap(null,c1,'1',"AA",2);creditRatingMapRepository.saveAll(Arrays.asList(m123));
            CreditRatingMap m124=new CreditRatingMap(null,c1,'1',"AA-",2);creditRatingMapRepository.saveAll(Arrays.asList(m124));
            CreditRatingMap m125=new CreditRatingMap(null,c1,'1',"A+",3);creditRatingMapRepository.saveAll(Arrays.asList(m125));
            CreditRatingMap m126=new CreditRatingMap(null,c1,'1',"A",3);creditRatingMapRepository.saveAll(Arrays.asList(m126));
            CreditRatingMap m127=new CreditRatingMap(null,c1,'1',"A-",4);creditRatingMapRepository.saveAll(Arrays.asList(m127));
            CreditRatingMap m128=new CreditRatingMap(null,c1,'1',"BBB+",4);creditRatingMapRepository.saveAll(Arrays.asList(m128));
            CreditRatingMap m129=new CreditRatingMap(null,c1,'1',"BBB",5);creditRatingMapRepository.saveAll(Arrays.asList(m129));
            CreditRatingMap m130=new CreditRatingMap(null,c1,'1',"BBB-",5);creditRatingMapRepository.saveAll(Arrays.asList(m130));
            CreditRatingMap m131=new CreditRatingMap(null,c1,'1',"BB+",6);creditRatingMapRepository.saveAll(Arrays.asList(m131));
            CreditRatingMap m132=new CreditRatingMap(null,c1,'1',"BB",6);creditRatingMapRepository.saveAll(Arrays.asList(m132));
            CreditRatingMap m133=new CreditRatingMap(null,c1,'1',"BB-",7);creditRatingMapRepository.saveAll(Arrays.asList(m133));
            CreditRatingMap m134=new CreditRatingMap(null,c1,'1',"B+",7);creditRatingMapRepository.saveAll(Arrays.asList(m134));
            CreditRatingMap m135=new CreditRatingMap(null,c1,'1',"B",8);creditRatingMapRepository.saveAll(Arrays.asList(m135));
            CreditRatingMap m136=new CreditRatingMap(null,c1,'1',"B-",8);creditRatingMapRepository.saveAll(Arrays.asList(m136));
            CreditRatingMap m137=new CreditRatingMap(null,c1,'1',"C+",8);creditRatingMapRepository.saveAll(Arrays.asList(m137));
            CreditRatingMap m138=new CreditRatingMap(null,c1,'1',"C",9);creditRatingMapRepository.saveAll(Arrays.asList(m138));
            CreditRatingMap m139=new CreditRatingMap(null,c1,'1',"C-",9);creditRatingMapRepository.saveAll(Arrays.asList(m139));
            CreditRatingMap m140=new CreditRatingMap(null,c1,'1',"D",10);creditRatingMapRepository.saveAll(Arrays.asList(m140));
            CreditRatingMap m141=new CreditRatingMap(null,c1,'0',"A1+",2);creditRatingMapRepository.saveAll(Arrays.asList(m141));
            CreditRatingMap m142=new CreditRatingMap(null,c1,'0',"A1",3);creditRatingMapRepository.saveAll(Arrays.asList(m142));
            CreditRatingMap m143=new CreditRatingMap(null,c1,'0',"A2+",4);creditRatingMapRepository.saveAll(Arrays.asList(m143));
            CreditRatingMap m144=new CreditRatingMap(null,c1,'0',"A2",5);creditRatingMapRepository.saveAll(Arrays.asList(m144));
            CreditRatingMap m145=new CreditRatingMap(null,c1,'0',"A3+",6);creditRatingMapRepository.saveAll(Arrays.asList(m145));
            CreditRatingMap m146=new CreditRatingMap(null,c1,'0',"A3",7);creditRatingMapRepository.saveAll(Arrays.asList(m146));
            CreditRatingMap m147=new CreditRatingMap(null,c1,'0',"A4+",8);creditRatingMapRepository.saveAll(Arrays.asList(m147));
            CreditRatingMap m148=new CreditRatingMap(null,c1,'0',"A4",9);creditRatingMapRepository.saveAll(Arrays.asList(m148));
            CreditRatingMap m149=new CreditRatingMap(null,c1,'0',"D",10);creditRatingMapRepository.saveAll(Arrays.asList(m149));


            c1 = creditRatingSourceRepository.findByCode("S&P");
            CreditRatingMap m151=new CreditRatingMap(null,c1,'1',"AAA",1);creditRatingMapRepository.saveAll(Arrays.asList(m151));
            CreditRatingMap m152=new CreditRatingMap(null,c1,'1',"AA+",1);creditRatingMapRepository.saveAll(Arrays.asList(m152));
            CreditRatingMap m153=new CreditRatingMap(null,c1,'1',"AA",2);creditRatingMapRepository.saveAll(Arrays.asList(m153));
            CreditRatingMap m154=new CreditRatingMap(null,c1,'1',"AA-",2);creditRatingMapRepository.saveAll(Arrays.asList(m154));
            CreditRatingMap m155=new CreditRatingMap(null,c1,'1',"A+",3);creditRatingMapRepository.saveAll(Arrays.asList(m155));
            CreditRatingMap m156=new CreditRatingMap(null,c1,'1',"A",3);creditRatingMapRepository.saveAll(Arrays.asList(m156));
            CreditRatingMap m157=new CreditRatingMap(null,c1,'1',"A-",4);creditRatingMapRepository.saveAll(Arrays.asList(m157));
            CreditRatingMap m158=new CreditRatingMap(null,c1,'1',"BBB+",4);creditRatingMapRepository.saveAll(Arrays.asList(m158));
            CreditRatingMap m159=new CreditRatingMap(null,c1,'1',"BBB",5);creditRatingMapRepository.saveAll(Arrays.asList(m159));
            CreditRatingMap m160=new CreditRatingMap(null,c1,'1',"BBB-",5);creditRatingMapRepository.saveAll(Arrays.asList(m160));
            CreditRatingMap m161=new CreditRatingMap(null,c1,'1',"BB+",6);creditRatingMapRepository.saveAll(Arrays.asList(m161));
            CreditRatingMap m162=new CreditRatingMap(null,c1,'1',"BB",6);creditRatingMapRepository.saveAll(Arrays.asList(m162));
            CreditRatingMap m163=new CreditRatingMap(null,c1,'1',"BB-",7);creditRatingMapRepository.saveAll(Arrays.asList(m163));
            CreditRatingMap m164=new CreditRatingMap(null,c1,'1',"B+",7);creditRatingMapRepository.saveAll(Arrays.asList(m164));
            CreditRatingMap m165=new CreditRatingMap(null,c1,'1',"B",8);creditRatingMapRepository.saveAll(Arrays.asList(m165));
            CreditRatingMap m166=new CreditRatingMap(null,c1,'1',"B-",8);creditRatingMapRepository.saveAll(Arrays.asList(m166));
            CreditRatingMap m167=new CreditRatingMap(null,c1,'1',"C+",8);creditRatingMapRepository.saveAll(Arrays.asList(m167));
            CreditRatingMap m168=new CreditRatingMap(null,c1,'1',"C",9);creditRatingMapRepository.saveAll(Arrays.asList(m168));
            CreditRatingMap m169=new CreditRatingMap(null,c1,'1',"C-",9);creditRatingMapRepository.saveAll(Arrays.asList(m169));
            CreditRatingMap m170=new CreditRatingMap(null,c1,'0',"D",10);creditRatingMapRepository.saveAll(Arrays.asList(m170));
            CreditRatingMap m171=new CreditRatingMap(null,c1,'0',"A1+",2);creditRatingMapRepository.saveAll(Arrays.asList(m171));
            CreditRatingMap m172=new CreditRatingMap(null,c1,'0',"A1",3);creditRatingMapRepository.saveAll(Arrays.asList(m172));
            CreditRatingMap m173=new CreditRatingMap(null,c1,'0',"A2",5);creditRatingMapRepository.saveAll(Arrays.asList(m173));
            CreditRatingMap m174=new CreditRatingMap(null,c1,'0',"A3",7);creditRatingMapRepository.saveAll(Arrays.asList(m174));
            CreditRatingMap m175=new CreditRatingMap(null,c1,'0',"B",8);creditRatingMapRepository.saveAll(Arrays.asList(m175));
            CreditRatingMap m176=new CreditRatingMap(null,c1,'0',"C",9);creditRatingMapRepository.saveAll(Arrays.asList(m176));
            CreditRatingMap m177=new CreditRatingMap(null,c1,'0',"D",10);creditRatingMapRepository.saveAll(Arrays.asList(m177));

            c1 = creditRatingSourceRepository.findByCode("Moodys");
            CreditRatingMap m179=new CreditRatingMap(null,c1,'1',"Aaa",1);creditRatingMapRepository.saveAll(Arrays.asList(m179));
            CreditRatingMap m180=new CreditRatingMap(null,c1,'1',"Aa1",1);creditRatingMapRepository.saveAll(Arrays.asList(m180));
            CreditRatingMap m181=new CreditRatingMap(null,c1,'1',"Aa2",2);creditRatingMapRepository.saveAll(Arrays.asList(m181));
            CreditRatingMap m182=new CreditRatingMap(null,c1,'1',"Aa3",2);creditRatingMapRepository.saveAll(Arrays.asList(m182));
            CreditRatingMap m183=new CreditRatingMap(null,c1,'1',"A1",3);creditRatingMapRepository.saveAll(Arrays.asList(m183));
            CreditRatingMap m184=new CreditRatingMap(null,c1,'1',"A2",3);creditRatingMapRepository.saveAll(Arrays.asList(m184));
            CreditRatingMap m185=new CreditRatingMap(null,c1,'1',"A3",4);creditRatingMapRepository.saveAll(Arrays.asList(m185));
            CreditRatingMap m186=new CreditRatingMap(null,c1,'1',"Baa1",4);creditRatingMapRepository.saveAll(Arrays.asList(m186));
            CreditRatingMap m187=new CreditRatingMap(null,c1,'1',"Baa2",5);creditRatingMapRepository.saveAll(Arrays.asList(m187));
            CreditRatingMap m188=new CreditRatingMap(null,c1,'1',"Baa3",5);creditRatingMapRepository.saveAll(Arrays.asList(m188));
            CreditRatingMap m189=new CreditRatingMap(null,c1,'1',"Ba1",6);creditRatingMapRepository.saveAll(Arrays.asList(m189));
            CreditRatingMap m190=new CreditRatingMap(null,c1,'1',"Ba2",6);creditRatingMapRepository.saveAll(Arrays.asList(m190));
            CreditRatingMap m191=new CreditRatingMap(null,c1,'1',"Ba3",7);creditRatingMapRepository.saveAll(Arrays.asList(m191));
            CreditRatingMap m192=new CreditRatingMap(null,c1,'1',"B1",7);creditRatingMapRepository.saveAll(Arrays.asList(m192));
            CreditRatingMap m193=new CreditRatingMap(null,c1,'1',"B2",8);creditRatingMapRepository.saveAll(Arrays.asList(m193));
            CreditRatingMap m194=new CreditRatingMap(null,c1,'1',"B3",8);creditRatingMapRepository.saveAll(Arrays.asList(m194));
            CreditRatingMap m195=new CreditRatingMap(null,c1,'1',"Caa1",8);creditRatingMapRepository.saveAll(Arrays.asList(m195));
            CreditRatingMap m196=new CreditRatingMap(null,c1,'1',"Caa2",9);creditRatingMapRepository.saveAll(Arrays.asList(m196));
            CreditRatingMap m197=new CreditRatingMap(null,c1,'1',"Caa3",9);creditRatingMapRepository.saveAll(Arrays.asList(m197));
            CreditRatingMap m198=new CreditRatingMap(null,c1,'1',"Ca",10);creditRatingMapRepository.saveAll(Arrays.asList(m198));
            CreditRatingMap m199=new CreditRatingMap(null,c1,'1',"C",10);creditRatingMapRepository.saveAll(Arrays.asList(m199));
            CreditRatingMap m200=new CreditRatingMap(null,c1,'0',"P1",3);creditRatingMapRepository.saveAll(Arrays.asList(m200));
            CreditRatingMap m201=new CreditRatingMap(null,c1,'0',"P2",5);creditRatingMapRepository.saveAll(Arrays.asList(m201));
            CreditRatingMap m202=new CreditRatingMap(null,c1,'0',"P3",7);creditRatingMapRepository.saveAll(Arrays.asList(m202));
            CreditRatingMap m203=new CreditRatingMap(null,c1,'0',"NP",10);creditRatingMapRepository.saveAll(Arrays.asList(m203));



            c1 = creditRatingSourceRepository.findByCode("Fitch");
            CreditRatingMap m205=new CreditRatingMap(null,c1,'1',"AAA",1);creditRatingMapRepository.saveAll(Arrays.asList(m205));
            CreditRatingMap m206=new CreditRatingMap(null,c1,'1',"AA+",1);creditRatingMapRepository.saveAll(Arrays.asList(m206));
            CreditRatingMap m207=new CreditRatingMap(null,c1,'1',"AA",2);creditRatingMapRepository.saveAll(Arrays.asList(m207));
            CreditRatingMap m208=new CreditRatingMap(null,c1,'1',"AA-",2);creditRatingMapRepository.saveAll(Arrays.asList(m208));
            CreditRatingMap m209=new CreditRatingMap(null,c1,'1',"A+",3);creditRatingMapRepository.saveAll(Arrays.asList(m209));
            CreditRatingMap m210=new CreditRatingMap(null,c1,'1',"A",3);creditRatingMapRepository.saveAll(Arrays.asList(m210));
            CreditRatingMap m211=new CreditRatingMap(null,c1,'1',"A-",4);creditRatingMapRepository.saveAll(Arrays.asList(m211));
            CreditRatingMap m212=new CreditRatingMap(null,c1,'1',"BBB+",4);creditRatingMapRepository.saveAll(Arrays.asList(m212));
            CreditRatingMap m213=new CreditRatingMap(null,c1,'1',"BBB",5);creditRatingMapRepository.saveAll(Arrays.asList(m213));
            CreditRatingMap m214=new CreditRatingMap(null,c1,'1',"BBB-",5);creditRatingMapRepository.saveAll(Arrays.asList(m214));
            CreditRatingMap m215=new CreditRatingMap(null,c1,'1',"BB+",6);creditRatingMapRepository.saveAll(Arrays.asList(m215));
            CreditRatingMap m216=new CreditRatingMap(null,c1,'1',"BB",6);creditRatingMapRepository.saveAll(Arrays.asList(m216));
            CreditRatingMap m217=new CreditRatingMap(null,c1,'1',"BB-",7);creditRatingMapRepository.saveAll(Arrays.asList(m217));
            CreditRatingMap m218=new CreditRatingMap(null,c1,'1',"B+",7);creditRatingMapRepository.saveAll(Arrays.asList(m218));
            CreditRatingMap m219=new CreditRatingMap(null,c1,'1',"B",8);creditRatingMapRepository.saveAll(Arrays.asList(m219));
            CreditRatingMap m220=new CreditRatingMap(null,c1,'1',"B-",8);creditRatingMapRepository.saveAll(Arrays.asList(m220));
            CreditRatingMap m221=new CreditRatingMap(null,c1,'1',"CCC",8);creditRatingMapRepository.saveAll(Arrays.asList(m221));
            CreditRatingMap m222=new CreditRatingMap(null,c1,'1',"CC",9);creditRatingMapRepository.saveAll(Arrays.asList(m222));
            CreditRatingMap m223=new CreditRatingMap(null,c1,'1',"C",9);creditRatingMapRepository.saveAll(Arrays.asList(m223));
            CreditRatingMap m224=new CreditRatingMap(null,c1,'1',"RD",10);creditRatingMapRepository.saveAll(Arrays.asList(m224));
            CreditRatingMap m225=new CreditRatingMap(null,c1,'1',"D",10);creditRatingMapRepository.saveAll(Arrays.asList(m225));
            CreditRatingMap m226=new CreditRatingMap(null,c1,'0',"F1+",2);creditRatingMapRepository.saveAll(Arrays.asList(m226));
            CreditRatingMap m227=new CreditRatingMap(null,c1,'0',"F1",3);creditRatingMapRepository.saveAll(Arrays.asList(m227));
            CreditRatingMap m228=new CreditRatingMap(null,c1,'0',"F2",5);creditRatingMapRepository.saveAll(Arrays.asList(m228));
            CreditRatingMap m229=new CreditRatingMap(null,c1,'0',"F3",7);creditRatingMapRepository.saveAll(Arrays.asList(m229));
            CreditRatingMap m230=new CreditRatingMap(null,c1,'0',"B",8);creditRatingMapRepository.saveAll(Arrays.asList(m230));
            CreditRatingMap m231=new CreditRatingMap(null,c1,'0',"C",9);creditRatingMapRepository.saveAll(Arrays.asList(m231));
            CreditRatingMap m232=new CreditRatingMap(null,c1,'0',"RD",10);creditRatingMapRepository.saveAll(Arrays.asList(m232));
            CreditRatingMap m233=new CreditRatingMap(null,c1,'0',"D",10);creditRatingMapRepository.saveAll(Arrays.asList(m233));

            c1 = creditRatingSourceRepository.findByCode("PFS");
            CreditRatingMap m235=new CreditRatingMap(null,c1,'1',"PFS1",1);creditRatingMapRepository.saveAll(Arrays.asList(m235));
            CreditRatingMap m236=new CreditRatingMap(null,c1,'1',"PFS2",2);creditRatingMapRepository.saveAll(Arrays.asList(m236));
            CreditRatingMap m237=new CreditRatingMap(null,c1,'1',"PFS3",3);creditRatingMapRepository.saveAll(Arrays.asList(m237));
            CreditRatingMap m238=new CreditRatingMap(null,c1,'1',"PFS4",4);creditRatingMapRepository.saveAll(Arrays.asList(m238));
            CreditRatingMap m239=new CreditRatingMap(null,c1,'1',"PFS5",5);creditRatingMapRepository.saveAll(Arrays.asList(m239));
            CreditRatingMap m240=new CreditRatingMap(null,c1,'1',"PFS6",6);creditRatingMapRepository.saveAll(Arrays.asList(m240));
            CreditRatingMap m241=new CreditRatingMap(null,c1,'1',"PFS7",7);creditRatingMapRepository.saveAll(Arrays.asList(m241));
            CreditRatingMap m242=new CreditRatingMap(null,c1,'1',"PFS8",8);creditRatingMapRepository.saveAll(Arrays.asList(m242));
            CreditRatingMap m243=new CreditRatingMap(null,c1,'1',"PFS9",9);creditRatingMapRepository.saveAll(Arrays.asList(m243));
            CreditRatingMap m244=new CreditRatingMap(null,c1,'1',"PFS10",10);creditRatingMapRepository.saveAll(Arrays.asList(m244));

            c1 = creditRatingSourceRepository.findByCode("PFS");
            CreditRatingMap m245=new CreditRatingMap(null,c1,'0',"PFS1",1);creditRatingMapRepository.saveAll(Arrays.asList(m235));
            CreditRatingMap m246=new CreditRatingMap(null,c1,'0',"PFS2",2);creditRatingMapRepository.saveAll(Arrays.asList(m236));
            CreditRatingMap m247=new CreditRatingMap(null,c1,'0',"PFS3",3);creditRatingMapRepository.saveAll(Arrays.asList(m237));
            CreditRatingMap m248=new CreditRatingMap(null,c1,'0',"PFS4",4);creditRatingMapRepository.saveAll(Arrays.asList(m238));
            CreditRatingMap m249=new CreditRatingMap(null,c1,'0',"PFS5",5);creditRatingMapRepository.saveAll(Arrays.asList(m239));
            CreditRatingMap m250=new CreditRatingMap(null,c1,'0',"PFS6",6);creditRatingMapRepository.saveAll(Arrays.asList(m240));
            CreditRatingMap m251=new CreditRatingMap(null,c1,'0',"PFS7",7);creditRatingMapRepository.saveAll(Arrays.asList(m241));
            CreditRatingMap m252=new CreditRatingMap(null,c1,'0',"PFS8",8);creditRatingMapRepository.saveAll(Arrays.asList(m242));
            CreditRatingMap m253=new CreditRatingMap(null,c1,'0',"PFS9",9);creditRatingMapRepository.saveAll(Arrays.asList(m243));
            CreditRatingMap m254=new CreditRatingMap(null,c1,'0',"PFS10",10);creditRatingMapRepository.saveAll(Arrays.asList(m244));

            log.info("-------------------------- Added Credit Rating Data for : " + c1.getCode());

        }





    }
}
