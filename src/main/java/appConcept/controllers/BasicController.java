package appConcept.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import appConcept.dao.Detail;
import appConcept.dao.Employee;
import appConcept.dao.Team;
import appConcept.services.BasicService;

@Controller
public class BasicController {

	@Autowired
	private BasicService basicService;
	

	@GetMapping("/employees")												//initial page Emps & Teams
	public String getAllEmployees(Model model) {
		List<Employee> list = basicService.getEmployees();
		model.addAttribute("employees", list);
		List<Team> listTeam = basicService.getTeams();
		model.addAttribute("teams", listTeam);
		return "empindex";
	}

	@GetMapping("/empdetail")												//load Detail for Employee
	public String details(@RequestParam("empid") int id, Model model) {
		System.out.println("get employee "+id);
		Employee emp = basicService.getEmployee(id);
		model.addAttribute("employee", emp);
		Detail detail = basicService.getDetails(emp.getDetail().getId());
		System.out.println(detail.toString());
		model.addAttribute("detail", detail);
		return "empdetail";
	}
	
	@GetMapping("/detailupd")												//edit Detail - initialization (adding model attributes)
	public String detailsUpdate(@RequestParam("detailid") int id, Model model) {
		Detail detail = basicService.getDetails(id);
		model.addAttribute("detail", detail);
		model.addAttribute("id", detail.getEmployee().getId());
		return "detailupd";
	}
	
	
	@RequestMapping("/updatedet")											//processing edited Detail 
	public String detailsUpdateJob(@ModelAttribute("detail") Detail detail, @RequestParam("empid") int id) {
		detail.setEmployee(basicService.getEmployee(id));
		basicService.saveDetails(detail);
		return "redirect:/";
	}
	
	@GetMapping("/empedit")													//edit Employee - initialization
	public String editEmployee(@RequestParam("empid") int id, Model model) {
		Employee employee = basicService.getEmployee(id);
		model.addAttribute("employee", employee);
		model.addAttribute("id", String.valueOf(employee.getTeam().getId()));
		model.addAttribute("detailid", employee.getDetail().getId());
		List<Team> teams = basicService.getTeams();
		model.addAttribute("teams", teams);
		return "empedit";
	}
	
	@GetMapping("/addemployee")												//NEW Employee - initialization
	public String addemployee(Model model) {
		Employee employee = new Employee();
		employee.setId(0);													//setting up "PK" for Employee before persisting via saveOrUpdate();
		model.addAttribute("teams", basicService.getTeams());
		model.addAttribute("employee", employee);
		model.addAttribute("detailid", 0);									//setting up "PK" for Detail (for the newly created Employee) before persisting via saveOrUpdate();
		return "empedit";
	}
	
	
	@RequestMapping("empupdate")											//processing edited Employee - update if existing | initialize object & set Team before persisting
	public String empupdate(@RequestParam("teamid") int teamId, @RequestParam("detailid") int detailId, @ModelAttribute("employee") Employee employee, Model model) {
		System.out.println(teamId+" empupdate saving update employee / team id");
		System.out.println(detailId+" empupdate detailId");
		if (detailId==0) {													//if no Detail assigned to Employee - persist Employee and move to definition of new Detail
			employee.setTeam(basicService.getTeam(teamId));
			int empId=basicService.saveEmployee(employee);
			
			Detail detail = new Detail();
			detail.setEmployee(basicService.getEmployee(empId));
			basicService.saveDetails(detail);
			
			model.addAttribute("detail", detail);
			model.addAttribute("id",empId);
			return "detailupd";												//if new employee (ID==0), move to define Details
		}
		employee.setDetail(basicService.getDetails(detailId));
		employee.setTeam(basicService.getTeam(teamId));
		System.out.println(employee.toString());
		basicService.saveEmployee(employee);
		return "redirect:/";
	}
	
	
	@GetMapping("/empremove")												//remove Employee, cascade.all for corresponding Detail 
	public String removeEmployee(@RequestParam("empid") int id, Model model) {
		basicService.removeEmployee(id);
		return "redirect:/";
	}

	@RequestMapping("/teamcreate")											//NEW Team - initialization
	public String createNewTeam(Model model) {
		Team team = new Team();
		team.setId(0);
		model.addAttribute("team", team);
		return "teamedit";
	}
	
	@RequestMapping("/teamedit")											//Team - initialization
	public String editCreatedTeam(@RequestParam("teamid") int id, Model model) {
		model.addAttribute("team", basicService.getTeam(id));
		return "teamedit";
	}
	
	@RequestMapping("/processteam")											//processing edited Team
	public String processTeam(@Valid @ModelAttribute("team") Team team, BindingResult br, Model model) {
		if (br.hasErrors()) {
				if (team.getId()==0) {										//failed @Valid of a new team addition, returning empty Team with id 0
					Team newTeam = new Team();
					newTeam.setId(0);
					model.addAttribute("team", team);
					return "teamedit";
				}
				else {														//failed @Valid when editing existing team, returning existing team to validate - will cause error response to not be shown
					model.addAttribute("team", basicService.getTeam(team.getId()));
					return "teamedit";
				}
		}
		basicService.saveTeam(team);										//validation successful, persisting
		return "redirect:/";
	}
	
	
}
