/**
 * 
 */
 
 function getReimbItems(){ //getting all reimbursement items

	
	
	let reimbsUrl = "http://localhost:8000/reimbursements/employee";
	
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
    let idColumn = document.createElement("td");
    let amountColumn = document.createElement("td");
    let statusColumn = document.createElement("td");
    let submitReuestDateColumn = document.createElement("td");
    let approvalReuestDateColumn = document.createElement("td");
   
    //assigning the "text value" to our columns 

    idColumn.innerText = reimbList.id;
    amountColumn.innerText = reimbList.amount;
    statusColumn.innerText = reimbList.status;
    submitReuestDateColumn.innerText = reimbList.submitRequestDate;
    approvalReuestDateColumn.innerText = reimbList.approvalRequestDate;

    //attach the columns to our newly created row 
    tableRow.appendChild(idColumn);
    tableRow.appendChild(amountColumn);
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

let button = document.getElementById("requestSubmitButton");
button.addEventListener('click',submitRequest);
	
function submitRequest(){//invoke this method when there is a change in the submit button
    let reimbType;
    let radio = document.getElementsByClassName("form-check");
    for(i = 0; i < radio.length; i++) {
        if(radio[i].checked)
        reimbType = radio[i].value;
    }
    let amount = document.getElementById("Amount");
    let description = document.getElementById("Description");
    fetch("http://localhost:8000/employeeRequest", {
        mehod: 'POST',
        headers: {
            'Content-type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify({
            'reimbursement_Type': reimbType,//add this column to the table or update in functions
            'amount_Reimbursed': amount,  
            'Description': row.description,   
        })
    })
}




