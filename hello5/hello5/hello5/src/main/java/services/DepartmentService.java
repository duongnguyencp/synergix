package services;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.DepartmentDAO;
import entities.Department;
@RequestScoped
public class DepartmentService {
	DepartmentDAO departmentDao=new DepartmentDAO();
	private   List<Department> departments = departmentDao.getListDepartment();
	public Department find(int id) {
		DepartmentDAO departmentDAO=new DepartmentDAO();
		List<Department> lsDepartment=departmentDAO.getListDepartment();
		for(Department d:lsDepartment) {
			if(d.getId()==id) {
				return d;
			}
		}
		return null;
	}
	public List<Department> getDepartments() {
		return departments;
	}
	public void addDepartment(Department department) {
		departmentDao.addDepartment(department);
		departments.add(department);
		
	}
	public void deleteDepartment(Department department) {
		departments.remove(department);
		departmentDao.deleteDepartment(department.getId());
	}

	public void editDepartment(Department department) {
		for(Department e:departments) {
			e.setCanEdit(false);
		}
		department.setCanEdit(true);
	}
	public void cancelEdit(Department department) {
		department.setCanEdit(false);
	}
	public void saveDepartments(Department department) {
		departments.set(departments.indexOf(department),department);
		departmentDao.updateDepartment(department.getId(), department);
		cancelEdit(department);
	}
}
