package org.gdal.geomap;

import org.gdal.gdal.gdal;
import org.gdal.ogr.*;

public class WriteDemo {

    public static void main(String[] args) {
        WriteDemo readshpObj = new WriteDemo();
        readshpObj.WriteVectorFile();
    }
    static void WriteVectorFile() {
        //路径不能包含中文
        String strVectorFile = "D:\\test.shp";

        ogr.RegisterAll();
        gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "NO");
        gdal.SetConfigOption("SHAPE_ENCODING", "CP936");

        String strDriverName = "ESRI Shapefile";
        Driver oDriver = ogr.GetDriverByName(strDriverName);
        if (oDriver == null) {
            System.out.println(strVectorFile + " 驱动不可用！\n");
            return;
        }
        DataSource oDS = oDriver.CreateDataSource(strVectorFile, null);
        if (oDS == null) {
            System.out.println("创建矢量文件【" + strVectorFile + "】失败！\n");
            return;
        }

        Layer oLayer = oDS.CreateLayer("TestPolygon", null, ogr.wkbPolygon, null);
        if (oLayer == null) {
            System.out.println("图层创建失败！\n");
            return;
        }

        // 下面创建属性表
        // 先创建一个叫FieldID的整型属性
        FieldDefn oFieldID = new FieldDefn("FieldID", ogr.OFTInteger);
        oLayer.CreateField(oFieldID);

        // 再创建一个叫FeatureName的字符型属性，字符长度为50
        FieldDefn oFieldName = new FieldDefn("FieldName", ogr.OFTString);
        oFieldName.SetWidth(100);
        oLayer.CreateField(oFieldName);

        FeatureDefn oDefn = oLayer.GetLayerDefn();

        // 创建三角形要素
        Feature oFeatureTriangle = new Feature(oDefn);
        oFeatureTriangle.SetField(0, 0);
        oFeatureTriangle.SetField(1, "三角形");
        Geometry geomTriangle = Geometry.CreateFromWkt("POLYGON ((0 0,20 0,10 15,0 0))");
        oFeatureTriangle.SetGeometry(geomTriangle);
        oLayer.CreateFeature(oFeatureTriangle);

        // 创建矩形要素
        Feature oFeatureRectangle = new Feature(oDefn);
        oFeatureRectangle.SetField(0, 1);
        oFeatureRectangle.SetField(1, "矩形");
        Geometry geomRectangle = Geometry.CreateFromWkt("POLYGON ((30 0,60 0,60 30,30 30,30 0))");
        oFeatureRectangle.SetGeometry(geomRectangle);
        oLayer.CreateFeature(oFeatureRectangle);

        // 创建五角形要素
        Feature oFeaturePentagon = new Feature(oDefn);
        oFeaturePentagon.SetField(0, 2);
        oFeaturePentagon.SetField(1, "五角形");
        Geometry geomPentagon = Geometry.CreateFromWkt("POLYGON ((70 0,85 0,90 15,80 30,65 15,70 0))");
        oFeaturePentagon.SetGeometry(geomPentagon);
        oLayer.CreateFeature(oFeaturePentagon);

        oDS.SyncToDisk();
        System.out.println("\n数据集创建完成！\n");
    }

}
