package com.sa2021.stulist.excel;

import com.sa2021.stulist.model.Student;

import org.springframework.batch.item.ItemProcessor;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class StudentItemProcessor implements ItemProcessor<Student, Student> {

	@Override
	public Student process(final Student student) throws Exception {
		final String id = student.getId();
		final String name = student.getName();
		final String department = student.getDepartment();
		final String phone = student.getPhone();

		final Student transformed = new Student();
		transformed.setId(id);
		transformed.setName(processName(name));
		transformed.setDepartment(department);
		transformed.setBirthDate("");
		transformed.setBirthPlace("");
		transformed.setSex("");
		transformed.setPhone(phone);
		return transformed;
	}

	private String processName(final String name) throws BadHanyuPinyinOutputFormatCombination {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		String firstname = PinyinHelper.toHanYuPinyinString(name.substring(1), format, "", true);
		String lastname = PinyinHelper.toHanYuPinyinString(name.substring(0, 1), format, "", true);
		return firstUpper(lastname) + " " + firstUpper(firstname);
	}

	private String firstUpper(final String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
}
