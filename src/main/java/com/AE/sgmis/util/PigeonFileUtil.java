package com.AE.sgmis.util;

import com.AE.sgmis.exceptions.FileParseException;
import com.AE.sgmis.exceptions.FileSaveException;
import com.AE.sgmis.pojo.Pigeon;
import com.AE.sgmis.pojo.PigeonInfo;
import com.AE.sgmis.pojo.PigeonWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * 鸽子文件解析工具
 */
@Slf4j
@Component
public class PigeonFileUtil {

    /**
     * 旧版.xls解析工具
     * Office 2007- XML
     */
    public List<Map<String, PigeonWrapper>> getPigeonByXls(MultipartFile file, Map<String, Set<String>> xxpzMap) {
        List<Map<String, PigeonWrapper>> pigeonWrappers;

        try {
            HSSFWorkbook sheets = new HSSFWorkbook(file.getInputStream());
            HSSFSheet sheet = sheets.getSheetAt(0);
            pigeonWrappers = parsePojo(sheet, xxpzMap);
            sheets.close();
        } catch (IOException e) {
            log.error("检查文件 {} 时文件流发生错误", file, e);
            throw new FileSaveException("文件保存失败");
        }

        return pigeonWrappers;
    }

    /**
     * 新版.xlsx解析工具
     * Office 2007+ XML
     */
    public List<Map<String, PigeonWrapper>> getPigeonByXlsx(MultipartFile file, Map<String, Set<String>> xxpzMap) {
        List<Map<String, PigeonWrapper>> pigeonWrappers;

        try {
            XSSFWorkbook sheets = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = sheets.getSheetAt(0);
            pigeonWrappers = parsePojo(sheet, xxpzMap);
            sheets.close();
        } catch (IOException e) {
            log.error("检查文件 {} 时文件流发生错误", file, e);
            throw new FileSaveException("文件保存失败");
        }

        return pigeonWrappers;
    }

    /**
     * 解析表格
     * 足环号 副环号 鸽子名称 血统 性别 眼砂 羽色 类型 赛绩描述 父名字 父足环号 母名字 母足环号 状态
     */
    private List<Map<String, PigeonWrapper>> parsePojo(Sheet sheet, Map<String, Set<String>> xxpzMap) {
        List<Map<String, PigeonWrapper>> pigeonWrapperList = new ArrayList<>();
        sheet.forEach(row -> {
            if (row.getRowNum() != 0) {
                HashMap<String, PigeonWrapper> pigeonMap = new HashMap<>();
                //子代pigeon
                Pigeon pigeon = new Pigeon();
                pigeon.setRingNumber(getRingNumber(row, xxpzMap));
                pigeon.setName(getName(row));
                pigeon.setBloodline(getBloodline(row));
                pigeon.setSex(getSex(row));
                pigeon.setYan(getYan(row, xxpzMap));
                pigeon.setYs(getYs(row, xxpzMap));
                pigeon.setLx(getLx(row, xxpzMap));
                pigeon.setState(getState(row, xxpzMap));
                //子代pigeonInfo
                PigeonInfo pigeonInfo = new PigeonInfo();
                pigeonInfo.setSubRingNumber(getSubRingNumber(row));
                pigeonInfo.setDetail(getDetail(row));
                //子代pigeonWrapper
                PigeonWrapper pigeonWrapper = new PigeonWrapper();
                pigeonWrapper.setPigeon(pigeon);
                pigeonWrapper.setPigeonInfo(pigeonInfo);
                pigeonMap.put("pigeon", pigeonWrapper);
                //父亲
                String fatherRingNumber = getFatherRingNumber(row, xxpzMap);
                if (fatherRingNumber != null) {
                    //父亲pigeon
                    Pigeon fatherPigeon = new Pigeon();
                    fatherPigeon.setRingNumber(fatherRingNumber);
                    fatherPigeon.setSex("雄");
                    fatherPigeon.setName(getFatherName(row));
                    //父亲pigeonWrapper
                    PigeonWrapper fatherPigeonWrapper = new PigeonWrapper();
                    fatherPigeonWrapper.setPigeon(fatherPigeon);
                    pigeonMap.put("father", fatherPigeonWrapper);
                }
                //母亲
                String motherRingNumber = getMotherRingNumber(row, xxpzMap);
                if (motherRingNumber != null) {
                    //母亲pigeon
                    Pigeon motherPigeon = new Pigeon();
                    motherPigeon.setRingNumber(motherRingNumber);
                    motherPigeon.setSex("雌");
                    motherPigeon.setName(getMotherName(row));
                    //母亲pigeonWrapper
                    PigeonWrapper motherPigeonWrapper = new PigeonWrapper();
                    motherPigeonWrapper.setPigeon(motherPigeon);
                    pigeonMap.put("mother", motherPigeonWrapper);
                }
                pigeonWrapperList.add(pigeonMap);
            }
        });
        return pigeonWrapperList;
    }

    /**
     * 获取并检验
     * 足环号
     * 固定行：0
     */
    private String getRingNumber(Row row, Map<String, Set<String>> xxpzMap) {
        Cell cell = row.getCell(0);
        if (cell == null || cell.getCellType() != CellType.STRING) {
            throw new FileParseException("足环号异常");
        }
        String ringNumber = cell.getStringCellValue();
        //检验
        verifyRingNumber(ringNumber, xxpzMap);
        return ringNumber;
    }

    /**
     * 获取并检验
     * 副环号
     * 固定行：1
     */
    private String getSubRingNumber(Row row) {
        Cell cell = row.getCell(1);
        if (cell == null || cell.getCellType() != CellType.STRING) {
            return null;
        }
        return cell.getStringCellValue();
    }

    /**
     * 获取并检验
     * 鸽子名称
     * 固定行：2
     */
    private String getName(Row row) {
        Cell cell = row.getCell(2);
        if (cell == null || cell.getCellType() != CellType.STRING) {
            return null;
        }
        return cell.getStringCellValue();
    }

    /**
     * 获取并检验
     * 血统
     * 固定行：3
     */
    private String getBloodline(Row row) {
        Cell cell = row.getCell(3);
        if (cell == null || cell.getCellType() != CellType.STRING) {
            return null;
        }
        return cell.getStringCellValue();
    }

    /**
     * 获取并检验
     * 性别
     * 固定行：4
     */
    private String getSex(Row row) {
        Cell cell = row.getCell(4);
        if (cell == null || cell.getCellType() != CellType.STRING) {
            throw new FileParseException("性别异常");
        }
        String sex = cell.getStringCellValue();
        //检验
        if (!sex.equals("雄") && !sex.equals("雌")) {
            throw new FileParseException("性别异常");
        }
        return sex;
    }

    /**
     * 获取并检验
     * 眼砂
     * 固定行：5
     */
    private String getYan(Row row, Map<String, Set<String>> xxpzMap) {
        Cell cell = row.getCell(5);
        if (cell == null || cell.getCellType() != CellType.STRING) {
            return null;
        }
        String yan = cell.getStringCellValue();
        //检验
        if (!xxpzMap.get("yanpz").contains(yan)) {
            throw new FileParseException("眼砂异常");
        }
        return yan;
    }

    /**
     * 获取并检验
     * 羽色
     * 固定行：6
     */
    private String getYs(Row row, Map<String, Set<String>> xxpzMap) {
        Cell cell = row.getCell(6);
        if (cell == null || cell.getCellType() != CellType.STRING) {
            return null;
        }
        String ys = cell.getStringCellValue();
        //检验
        if (!xxpzMap.get("yspz").contains(ys)) {
            throw new FileParseException("羽色异常");
        }
        return ys;
    }

    /**
     * 获取并检验
     * 类别
     * 固定行：7
     */
    private String getLx(Row row, Map<String, Set<String>> xxpzMap) {
        Cell cell = row.getCell(7);
        if (cell == null || cell.getCellType() != CellType.STRING) {
            return null;
        }
        String lx = cell.getStringCellValue();
        //检验
        if (!xxpzMap.get("lxpz").contains(lx)) {
            throw new FileParseException("类型异常");
        }
        return lx;
    }

    /**
     * 获取并检验
     * 赛绩描述
     * 固定行：8
     */
    private String getDetail(Row row) {
        Cell cell = row.getCell(8);
        if (cell == null || cell.getCellType() != CellType.STRING) {
            return null;
        }
        return cell.getStringCellValue();
    }

    /**
     * 获取并检验
     * 父名称
     * 固定行：9
     */
    private String getFatherName(Row row) {
        Cell cell = row.getCell(9);
        if (cell == null || cell.getCellType() != CellType.STRING) {
            return null;
        }
        return cell.getStringCellValue();
    }

    /**
     * 获取并检验
     * 父足环
     * 固定行：10
     */
    private String getFatherRingNumber(Row row, Map<String, Set<String>> xxpzMap) {
        Cell cell = row.getCell(10);
        if (cell == null || cell.getCellType() != CellType.STRING) {
            return null;
        }
        String fatherRingNumber = cell.getStringCellValue();
        //检验
        verifyRingNumber(fatherRingNumber, xxpzMap);
        return fatherRingNumber;
    }

    /**
     * 获取并检验
     * 母名称
     * 固定行：11
     */
    private String getMotherName(Row row) {
        Cell cell = row.getCell(11);
        if (cell == null || cell.getCellType() != CellType.STRING) {
            return null;
        }
        return cell.getStringCellValue();
    }

    /**
     * 获取并检验
     * 母足环
     * 固定行：12
     */
    private String getMotherRingNumber(Row row, Map<String, Set<String>> xxpzMap) {
        Cell cell = row.getCell(12);
        if (cell == null || cell.getCellType() != CellType.STRING) {
            return null;
        }
        String motherRingNumber = cell.getStringCellValue();
        //检验
        verifyRingNumber(motherRingNumber, xxpzMap);
        return motherRingNumber;
    }

    /**
     * 获取并检验
     * 状态
     * 固定行：13
     */
    private String getState(Row row, Map<String, Set<String>> xxpzMap) {
        Cell cell = row.getCell(13);
        if (cell == null || cell.getCellType() != CellType.STRING) {
            return null;
        }
        String state = cell.getStringCellValue();
        //检验
        if (!xxpzMap.get("state").contains(state)) {
            throw new FileParseException("状态异常");
        }
        return state;
    }

    /**
     * 检验足环规范
     */
    private void verifyRingNumber(String ringNumber, Map<String, Set<String>> xxpzMap) {
        //检验
        String[] ringNumberSplit = ringNumber.split("-");
        Set<String> country = xxpzMap.get("country");
        Set<String> province = xxpzMap.get("province");
        //国家
        if (!country.contains(ringNumberSplit[0])) {
            throw new FileParseException("足环错误：不存在的国家代号");
        }
        //年份
        if (ringNumberSplit[1].length() != 4) {
            throw new FileParseException("足环错误：错误的年份");
        }
        //国家
        if (!province.contains(ringNumberSplit[2])) {
            throw new FileParseException("足环错误：不存在的省份代号");
        }
    }
}
