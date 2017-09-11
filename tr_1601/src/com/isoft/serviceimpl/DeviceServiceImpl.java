package com.isoft.serviceimpl;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.google.gson.Gson;
import com.isoft.dao.DeviceDAO;
import com.isoft.dao.TequipLogDao;
import com.isoft.model.Device;
import com.isoft.model.Tagency;
import com.isoft.model.Tattenchment;
import com.isoft.model.TdicCode;
import com.isoft.service.DeviceService;
import com.isoft.util.DateConvert;
import com.isoft.util.Page;
import com.isoft.util.UUIDUtils;

/**
 * @author f
 * @描述 设备管理业务实现
 * */
@Service("deviceServiceImpl")
public class DeviceServiceImpl implements DeviceService {
	private final static String excel2003L = ".xls"; // 2003- 版本的excel
	private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel
	// 注入dao
	@Resource
	private DeviceDAO deviceDAO;
	@Resource
	private TequipLogDao tequipLogDao;

	@Override
	public List<Device> selectDeviceAll(HttpServletRequest request) throws NumberFormatException, ParseException {
		String pageNow = request.getParameter("pageNow");
		if ("".equals(pageNow)) {
			pageNow = null;
		}
		Page page = null;

		List<Device> d_list = new ArrayList<Device>();
		// 取得总的记录数
		int totalCount = deviceDAO.getCounts();

		
		if (pageNow != null) {
			page = new Page(totalCount, Integer.parseInt(pageNow));
			page.setTotalPageCount(page.getTotalPageCount());
			d_list = this.deviceDAO.selectDeviceAll(page.getStartPos(), page.getPageSize());
			//进行时间改变
			for (Device d : d_list) {
				String s=DateConvert.convertDate(d.getT_check_date(), d.getT_check_circle());
				
				deviceDAO.updateDeviceLeftDay(DateConvert.convertDate(d.getT_check_date(),
							d.getT_check_circle()),d.getT_equip_id());
			}
			
		} else {
			page = new Page(totalCount, 1);
			page.setTotalPageCount(page.getTotalPageCount());
			d_list = this.deviceDAO.selectDeviceAll(page.getStartPos(), page.getPageSize());
			//进行时间改变
			for (Device d : d_list) {
				
			String s=DateConvert.convertDate(d.getT_check_date(), d.getT_check_circle());
				deviceDAO.updateDeviceLeftDay(DateConvert.convertDate(d.getT_check_date(), d.getT_check_circle()),d.getT_equip_id());
			}
		}

		request.setAttribute("page", page);

		return d_list;
	}

	@Override
	public String addDevice(Device device, String t_agency_name) throws ParseException {

		String s = ConvertDevice.convertInt(device.getT_dnumber());
		if ("date_exception".equals(s)) {
			return "date_exception";
		} else if ("date_error".equals(s)) {
			return "date_error";
		}

		// 判断这个供应商的id
		device.setT_equip_id(UUIDUtils.getId());
		device.setT_agency_id(deviceDAO.selectIdByTagencyName(t_agency_name));
		device.setT_check_date(device.getT_buy_date());

		device.setT_left_day(DateConvert.convertDate(device.getT_check_date(), device.getT_check_circle()));

		if (deviceDAO.addDevice(device) > 0) {
			return "add_success";
		} else {
			return "add_error";
		}

	}

	@Override
	public boolean deleteDevice(String did) {
		if (deviceDAO.deleteDevice(did) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Device updateDevice(String t_equip_id) {
		Device device = deviceDAO.selectDevice(t_equip_id);

		device.setT_dic_code(deviceDAO.selectDicNameById(device.getT_dic_code()));
		device.setT_agency_id(deviceDAO.selectDeviceDetail(device.getT_agency_id()).gettAgencyName());
		return device;
	}

	@Override
	public String selectDeviceLike(HttpServletRequest request) throws NumberFormatException, ParseException {
		String dname = request.getParameter("dname");
		String tdiccode_name = request.getParameter("tdiccode_name");
		if ("tdiccode_name_ALL".equals(tdiccode_name)) {
			tdiccode_name = "";
		}
		String pageNow = request.getParameter("pageNow");
		if ("".equals(pageNow)) {
			pageNow = null;
		}
		Page page = null;

		List<Device> d_list = new ArrayList<Device>();

		// 取得总的记录数
		int totalCount = Integer.parseInt(deviceDAO.getLikeCounts(dname.trim(), tdiccode_name.trim()));
		if (totalCount <= 5) {
			pageNow = null;
		}

		if (pageNow != null) {
			page = new Page(totalCount, Integer.parseInt(pageNow));
			page.setTotalPageCount(page.getTotalPageCount());
			d_list = this.deviceDAO.selectDeviceLike(page.getStartPos(), page.getPageSize(), dname.trim(),
					tdiccode_name.trim());
			//进行时间改变
			for (Device d : d_list) {
				
				
				deviceDAO.updateDeviceLeftDay(DateConvert.convertDate(d.getT_check_date(), d.getT_check_circle()),d.getT_equip_id());
			}
		} else {
			page = new Page(totalCount, 1);
			page.setTotalPageCount(page.getTotalPageCount());
			d_list = this.deviceDAO.selectDeviceLike(page.getStartPos(), page.getPageSize(), dname.trim(),
					tdiccode_name.trim());
			//进行时间改变
			for (Device d : d_list) {
				
				
				deviceDAO.updateDeviceLeftDay(DateConvert.convertDate(d.getT_check_date(), d.getT_check_circle()),d.getT_equip_id());
			}
		}
		for (Device device : d_list) {
			device.setT_dic_code(deviceDAO.selectDicNameById(device.getT_dic_code()));
		}

		Gson gson = new Gson();
		Map<Object,Object> map=new HashMap<Object, Object>();
		map.put("d_list", d_list);
		map.put("page", page);
		return gson.toJson(map);
	}

	// excel导出
	@Override
	public HSSFWorkbook exportDevice(List<Device> device) {
		String[] excelHeader = { "序号", "科室", "仪器名称", "购买日期", "上次年检时间", "年检周期", "距离下次检测", "提前预警" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Device");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(style);
			sheet.autoSizeColumn(i);
			sheet.setColumnWidth(2, 30 * 256);
			sheet.setColumnWidth(3, 30 * 256);
			sheet.setColumnWidth(4, 30 * 256);
		}

		for (int i = 0; i < device.size(); i++) {
			row = sheet.createRow(i + 1);
			Device d = device.get(i);
			row.createCell(0).setCellValue(i + 1);
			row.createCell(1).setCellValue(d.getT_dic_code());
			row.createCell(2).setCellValue(d.getT_device_name());
			row.createCell(3).setCellValue(d.getT_buy_date());
			row.createCell(4).setCellValue(d.getT_check_date());
			row.createCell(5).setCellValue(d.getT_check_circle());
			row.createCell(6).setCellValue(d.getT_left_day());
			row.createCell(7).setCellValue(d.getT_dnumber());

		}
		return wb;

	}

	// 设备详情
	@Override
	public String selectDeviceDetail(String t_agency_id,String t_equip_id) {
		Gson gson = new Gson();
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("tagency",deviceDAO.selectDeviceDetail(t_agency_id));
		map.put("tattenchment",deviceDAO.getImgSrc(t_equip_id));
		return gson.toJson(map);
	
	}

	@Override
	public List<Tagency> selectTagencyAll() {

		return deviceDAO.selectTagencyAll();
	}

	@Override
	public boolean updateDeviceTrue(Device device) {

		device.setT_dic_code(deviceDAO.selectDicCodeByDicName(device.getT_dic_code()));

		if (deviceDAO.updateDevice(device) > 0) {
			return true;
		}
		return false;
	}

	// 通过输入流读取excel
	@Override
	public List<List<Object>> getDeviceListByExcel(InputStream in, String fileName) throws Exception {
		List<List<Object>> list = null;

		Workbook work = this.getWorkbook(in, fileName);
		if (null == work) {
			System.out.println("工作簿为空");
		}

		Sheet sheet = null;
		Row row = null;
		Cell cell = null;

		list = new ArrayList<List<Object>>();

		for (int i = 0; i < work.getNumberOfSheets(); i++) {
			sheet = work.getSheetAt(i);
			if (sheet == null) {
				continue;
			}

			for (int j = sheet.getFirstRowNum(); j < sheet.getLastRowNum(); j++) {
				row = sheet.getRow(j);
				if (row == null || row.getFirstCellNum() == j) {
					continue;
				}

				List<Object> li = new ArrayList<Object>();
				for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
					cell = row.getCell(y);
					li.add(this.getCellValue(cell));
				}
				list.add(li);
			}
		}

		return list;
	}

	public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
		Workbook wb = null;
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		if (excel2003L.equals(fileType)) {
			wb = new HSSFWorkbook(inStr);
		} else if (excel2007U.equals(fileType)) {
			wb = new HSSFWorkbook(inStr);
		} else {
			throw new Exception("解析的文件格式有误！");
		}
		return wb;
	}

	public Object getCellValue(Cell cell) {
		Object value = null;
		DecimalFormat df = new DecimalFormat("0");
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		DecimalFormat df2 = new DecimalFormat("0.00");

		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			value = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if ("General".equals(cell.getCellStyle().getDataFormatString())) {
				value = df.format(cell.getNumericCellValue());
			} else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
				value = sdf.format(cell.getDateCellValue());
			} else {
				value = df2.format(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			value = "";
			break;
		default:
			break;
		}
		return value;
	}
	// 通过excel导入添加

	@Override
	public boolean addDeviceExcel(Device device) {

		return deviceDAO.addDevice(device) > 0 ? true : false;

	}

	// 查询所有科室
	@Override
	public List<TdicCode> selecTdicCodesAll() {

		return deviceDAO.selectTdicCodeAll();
	}

	// 设备检修查看检修人
	@Override
	public String selectCheckPerson(String dic_id) {
		String sid = deviceDAO.selectCheckDicId(dic_id);

		return deviceDAO.selectCheckPerson(sid);
	}

	// 确认检修
	@Transactional
	@Override
	public String checkDeviceTrue(HttpServletRequest request)  {
		String t_equip_id_check = request.getParameter("t_equip_id_check");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String tmp=sdf.format(new Date());
		Device tmpDevice=deviceDAO.selectDevice(t_equip_id_check);
		tmpDevice.setT_check_date(tmp);
		try{
			deviceDAO.updateDevice(tmpDevice);
			String t_left_day=DateConvert.convertDate(tmp, tmpDevice.getT_check_circle());
			deviceDAO.updateDeviceLeftDay(t_left_day, tmpDevice.getT_equip_id());
			return "check_success";
		}
		catch(Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return "check_error";
		}

	}

	@Override
	public List<Device> excelExport() {
		return deviceDAO.excelExport();
	}

}

class ConvertDevice {
	static String convertInt(String s) {
		try {
			int tmp = Integer.parseInt(s);
			if ((tmp > 365) || tmp <= 0) {
				return "date_exception";
			}
			return "date_success";
		} catch (Exception e) {
			return "date_error";
		}

	}

}
