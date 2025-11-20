const NAMES_POOL = ['Ravi', 'Sai', 'Rajesh', 'Raj', 'Rajeshwari', 'Bhavya'];
const DATA = new Array(1000).fill(0).map((_, index) => ({
  name: NAMES_POOL[index % NAMES_POOL.length],
  age: index,
}));

const BASIC_TEMPLATE1 = {
  data: DATA,
  template: {
    // This is the "root" view of your row (the LinearLayout)
    type: 'View',
    style: {
      padding: 56,
      backgroundColor: '#0055ff',
      marginVertical: 20,
      // orientation: 'HORIZONTAL',
      orientation: 'VERTICAL',
    },
    children: [
      {
        // This is the child TextView
        type: 'Text',
        // We add a special key 'id' so we know which view to update
        id: 'myTextView',
        style: {
          fontSize: 18,
          textColor: '#000000',
          gravity: 'CENTER_VERTICAL',
          backgroundColor: '#FFD700',
          // margin: 20,
        },
      },
      {
        // This is the child TextView
        type: 'Text',
        // We add a special key 'id' so we know which view to update
        id: 'myAgeTextView',
        style: {
          fontSize: 18,
          textColor: '#000000',
          gravity: 'CENTER_VERTICAL',
          backgroundColor: '#1E90FF',
        },
      },
      {
        type: 'View',
        style: {
          backgroundColor: '#LTGRAY', // Use hex strings
          // padding: 24,
          orientation: 'HORIZONTAL',
          // orientation: 'VERTICAL',
        },
        children: [
          {
            // This is the child TextView
            type: 'Text',
            // We add a special key 'id' so we know which view to update
            id: 'myTextView2',
            style: {
              fontSize: 24,
              textColor: '#00FF00',
              gravity: 'CENTER_VERTICAL',
              backgroundColor: '#1E90FF',
            },
          },
          {
            // This is the child TextView
            type: 'Text',
            // We add a special key 'id' so we know which view to update
            id: 'myAgeTextView2',
            style: {
              fontSize: 18,
              textColor: '#000000',
              gravity: 'CENTER_VERTICAL',
              backgroundColor: '#1E90FF',
            },
          },
        ],
      },
    ],
  },
  dataFitter: {
    myTextView: {
      key: 'name',
      type: 'string',
    },
    myTextView2: {
      key: 'name',
      type: 'string',
    },
    myAgeTextView: {
      key: 'age',
      type: 'number',
    },
    myAgeTextView2: {
      key: 'age',
      type: 'number',
    },
  },
};

export default BASIC_TEMPLATE1;
