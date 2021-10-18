/**
 * 
 */
 
 function getReimbItem(){ //getting a single element -- check if relevant --not relevant--remove

	
	let baseUrl = "http://localhost:8000/reimburesements/";
	
	let fullReimbUrl = baseUrl +employeeId;
	
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		
		if(this.status == 200 && this.readyState == 4){
			let reimbItem = JSON.parse(this.responseText);
			console.log(reimbItem);
			
			addRow(reimbItem);
		}
	}
	
	xhttp.open("GET",fullReimbUrl);
	
	xhttp.send();
	
}

function getReimbItems(){ //getting all reimbursement items

	
	
	let reimbsUrl = "http://localhost:8000/reimbursements/financeManager";
	
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function (){ 
				
		console.log(this.readyState);
		
		if(this.readyState == 4 && this.status == 200){
			
			let reimbArray = JSON.parse(this.responseText);
			//console.log(reimbArray);
			
			addAllItems(reimbArray);
			
		}
	}
	
	xhttp.open("GET",reimbsUrl);
	
	xhttp.send();
	
}

let button = document.getElementById("check-submit-btn");
button.addEventListener('click',getReimbItems);//check this

window.onload = function(){

	getReimbItems();
	
//This functions get invoked when the page is loaded in!
}

function addRow(reimbList){
    //Append this onto my table, 
    
   
    let tableBody = document.getElementById("reimbList");

    //Creating a table row
    let tableRow = document.createElement("tr");

    //Create the columns
    let reimIdColumn = document.createElement("td");
    let empIdColumn = document.createElement("td");
    let reimbTypeColumn = document.createElement("td");
    let amountColumn = document.createElement("td");
    let descriptionColumn = document.createElement("td");
    let statusColumn = document.createElement("td");
    var array = ["Pending","Approved","Rejected"];
    var selectList = document.createElement("select");
    statusColumn.appendChild(selectList);
    selectList.className = "form-select";
    for (var i = 0; i < array.length; i++) {
        var option = document.createElement("option");
        
        option.value = array[i];
        option.text = array[i];
        selectList.appendChild(option);
    }
    let submitReuestDateColumn = document.createElement("td");
    let approvalReuestDateColumn = document.createElement("td");
   
    //assigning the "text value" to our columns 

    reimIdColumn.innerText = reimbList.reimbursement_id;
    empIdColumn.innerText = reimbList.fk2_id;
    reimbTypeColumn.innerText = reimbList.reimbursement_Type;
    amountColumn.innerText = reimbList.amount_Reimbursed;
    descriptionColumn.innerText = reimbList.Description;
    statusColumn.innerText = reimbList.Approval;
    submitReuestDateColumn.innerText = reimbList.request_Date_Time;
    approvalReuestDateColumn.innerText = reimbList.approved_Date_Time;

    //attach the columns to our newly created row 
    tableRow.appendChild(reimIdColumn);
    tableRow.appendChild(empIdColumn);
    tableRow.appendChild(reimbTypeColumn);
    tableRow.appendChild(amountColumn);
    tableRow.appendChild(descriptionColumn);
    tableRow.appendChild(statusColumn);
    tableRow.appendChild(submitReuestDateColumn);
    tableRow.appendChild(approvalReuestDateColumn);
    
    //attach the row itself to the table
    tableBody.appendChild(tableRow);

    	
}

function addAllItems(addReimbArray){
	
	for(let item of addReimbArray){
		addRow(item);
	}
	
	
}

function addCheckItems(addReimbCheckArray){

    
   let pendingCheck = document.getElementById("Pending").value;
   let approvedCheck = document.getElementById("Approved").value;
   let deniedCheck = document.getElementById("Denied").value;
	
	for(let item of addReimbCheckArray){

        if (pendingCheck==true && item.status == "Pending"){

            addRow(item);

        }else if (approvedCheck == true && item.status == "Approved"){

            addRow(item);
        }else if (deniedCheck == true && item.status == "Denied"){

            addRow(item);
            
        }
		
	}
	
	
}


function updateTwo(id, status){//invoke this method when there is a change in the submit button
    fetch("http://localhost:8000/reimbursements/financeManager", {
        mehod: 'POST',
        headers: {
            'Content-type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify({//pass only status
             'reimbursement_id': id,
           // 'reimbursement_Type': row.reimbType,//add this column to the table or update in functions
           // 'amount_Reimbursed': row.amount,  
            'status': status
           // 'Description': row.description, 
            //'reimbursement_id': row.getElementById(),
            //'reimbursement_Type': row.reimbType,//add this column to the table or update in functions
            //'amount_Reimbursed': row.amount,  
            //'status': row.status, 
            //'Description': row.description,   
            
        })
    })
}

$('.form-select').click(function () {
    var id = $(this).closest("tr").find('td:eq(0)').value();
    var status = $(this).closest("tr").find('td:eq(5)').text();
    console.log(id);
    console.log(status);
    updateTwo(id,status);
});


//check 
table.addEventListener("click", event => {
    const row = event.target.closest('tr'); 
    updateTwo(row);
    if (!row) return;
    console.log(row.id);
    console.log(row);
  });


  
