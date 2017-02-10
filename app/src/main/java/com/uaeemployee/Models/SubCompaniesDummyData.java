package com.uaeemployee.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubCompaniesDummyData {
    public  HashMap<SubCompanyData, List<SubSubCompanyData>> getData() {
        HashMap<SubCompanyData, List<SubSubCompanyData>> expandableListDetail = new HashMap<SubCompanyData, List<SubSubCompanyData>>();

        List<SubSubCompanyData> subCompaniesOne = new ArrayList<SubSubCompanyData>();
        subCompaniesOne.add(new SubSubCompanyData("Sub sub Company One","50","20","30"));
        subCompaniesOne.add(new SubSubCompanyData("Sub sub Company Two","33","99","12"));
        subCompaniesOne.add(new SubSubCompanyData("Sub sub Company Three","13","55","88"));
        subCompaniesOne.add(new SubSubCompanyData("Sub sub Company Four","18","78","44"));
        subCompaniesOne.add(new SubSubCompanyData("Sub sub Company Five","156","95","30"));

        List<SubSubCompanyData> subCompaniesTwo = new ArrayList<SubSubCompanyData>();
        subCompaniesTwo.add(new SubSubCompanyData("Sub sub Company One","50","20","30"));
        subCompaniesTwo.add(new SubSubCompanyData("Sub sub Company Two","33","99","12"));
        subCompaniesTwo.add(new SubSubCompanyData("Sub sub Company Three","13","55","88"));
        subCompaniesTwo.add(new SubSubCompanyData("Sub sub Company Four","18","78","44"));


        List<SubSubCompanyData> subCompaniesthree = new ArrayList<SubSubCompanyData>();
        subCompaniesthree.add(new SubSubCompanyData("Sub sub Company One","50","20","30"));
        subCompaniesthree.add(new SubSubCompanyData("Sub sub Company Two","33","99","12"));
        subCompaniesthree.add(new SubSubCompanyData("Sub sub Company Three","13","55","88"));


        List<SubSubCompanyData> subCompaniesFour = new ArrayList<SubSubCompanyData>();
        subCompaniesFour.add(new SubSubCompanyData("Sub sub Company One","50","20","30"));
        subCompaniesFour.add(new SubSubCompanyData("Sub sub Company Two","33","99","12"));

        List<SubSubCompanyData> subCompaniesFive = new ArrayList<SubSubCompanyData>();
        subCompaniesFive.add(new SubSubCompanyData("Sub sub Company One","50","20","30"));

        expandableListDetail.put(new SubCompanyData("Sub Company One","5"), subCompaniesOne);
        expandableListDetail.put(new SubCompanyData("Sub Company Two","4"), subCompaniesTwo);
        expandableListDetail.put(new SubCompanyData("Sub Company Three","3"), subCompaniesthree);
        expandableListDetail.put(new SubCompanyData("Sub Company Four","2"), subCompaniesFour);
        expandableListDetail.put(new SubCompanyData("Sub Company Five","1"), subCompaniesFive);
        return expandableListDetail;
    }
}