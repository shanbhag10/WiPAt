/**
 * Created by omkar on 12-01-2017.
 */
$(document).ready(function(){
    var st,en;
   var table=  $('#example').DataTable( {
       dom: 'Bfrtip',
       buttons: [

           'excel',
           'csv',
           'pdf',
           'print'
       ]
   } );

    $("#class").change(function() {
        $('#subjects')
            .find('option')
            .remove()
            .end();
        $.ajax({ url: "API/database.php?req=subject&cid="+$("#class").val(),
            context: document.body,
            success: function(data){
                data = JSON.parse(data); // Now it's an array
console.log(data);
                //console.log(data);
                $('#subjects').append($('<option>', {
                    value: "",
                    text : "Select Subject"
                }));

                $.each(data, function(i, item) {
                    $('#subjects').append($('<option>', {
                        value: item.subject_id,
                        text : item.Subject_name
                    }));

                   // alert(item.Subject_name);
                });


            }});
    });

    $("#subjects").change(function() {


    });



    $('.daterange').daterangepicker({
        ranges: {
            'Today': [moment(), moment()],
            'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
            'Last 7 Days': [moment().subtract(6, 'days'), moment()],
            'Last 30 Days': [moment().subtract(29, 'days'), moment()],
            'This Month': [moment().startOf('month'), moment().endOf('month')],
            'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        startDate: moment().subtract(29, 'days'),
        endDate: moment()
    }, function (start, end) {
        st=start;
        en=end;
       // window.alert("You chose: " + start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));
    });

    $( "#target" ).submit(function( event ) {
        table.clear().draw();
        $.ajax({ url: "API/database.php?req=rep1&cid="+$("#class").val()+"&sid="+$("#subject").val()+"&start="+st.format('YYYY-MM-DD')+"&end="+en.format('YYYY-MM-DD'),
        context: document.body,
            success: function(data){
                data = JSON.parse(data); // Now it's an array
                console.log(data);
                //console.log(data);
                $.each(data, function(i, item) {


                    table.row.add( [
                            item.student_id,
                        item.name,
                        item.date,
                        item.lecture_no,
                        item.attendance

                    ] ).draw();

                    // alert(item.Subject_name);
                });


            }});



        event.preventDefault();
    });



});
